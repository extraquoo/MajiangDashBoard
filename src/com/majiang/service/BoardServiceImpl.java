package com.majiang.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.majiang.constant.Constants;
import com.majiang.dao.BoardDao;
import com.majiang.dao.GameDao;
import com.majiang.model.Board;
import com.majiang.model.Game;


@Service("boardService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private GameDao gameDao;
	
	@Override
	public Board findByBoardId(int id) {
		return boardDao.findByBoardId(id);
	}

	@Override
	public List<Board> findAllBoards() {
		return boardDao.findAllBoards();
	}
	
	@Override
	public List<Board> findBoardsByGameId(int gameId) {
		return boardDao.findBoardsByGameId(gameId);
	}

	@Override
	public void saveOrUpdate(Board board) {
		if (findByBoardId(board.getId())==null) {
			boardDao.save(board);
		} else {
			boardDao.update(board);
		}

	}
	
	@Override
	public void saveGameBoard(Board board){
		constructComments(board);
		if(!StringUtils.isEmpty(board.getWinner())){
		CalulateAmount(board);
		populatePoolAmount(board);
		board.setBoardDate(new Date());
		//if self draw
		boardDao.save(board);				
		}
		else{
		boardDao.save(board);
		}
		//update no win draw 
		updateNoWinDraw(board);
	}

	@Override
	public Board CalulateAmount(Board board) {
			
		Game currentGame = gameDao.findById(board.getGame_id());
		BigDecimal baseAmount = currentGame.getBaseAmount();
		BigDecimal flowerAmount = currentGame.getFlowerAmount();
		int maxFlowers = currentGame.getMaxFlowers();
		String handType = board.getHandType();
		BigDecimal laziWinAmount = baseAmount.add(flowerAmount.multiply(BigDecimal.valueOf(maxFlowers)));
		BigDecimal finalWinAmount ;
		if(Constants.HUN_YI_SE.equals(handType)||Constants.PENG_PENG_HU.equals(handType)){
			finalWinAmount = baseAmount.add(flowerAmount.multiply(BigDecimal.valueOf(board.getFlowers())));			
		}
		else if(Constants.QING_YI_SE.equals(handType)||Constants.HUN_PENG.equals(handType)||Constants.DA_DIAO_CHE.equals(handType)){
			finalWinAmount = laziWinAmount;
		}
		else if(Constants.QUAN_FENG_XIANG.equals(handType) || Constants.QING_PENG.equals(handType)){
			finalWinAmount = laziWinAmount.multiply(BigDecimal.valueOf(2));
		}
		else{
			finalWinAmount = laziWinAmount.multiply(BigDecimal.valueOf(4));
		}
		
		//clear door double the win amount
		if(board.getClearDoor()){
			finalWinAmount = finalWinAmount.multiply(BigDecimal.valueOf(2));
		}
		
		// if  nowincount is not zero, double the win amount
		if(currentGame.getDrawCount()>0){
			finalWinAmount = finalWinAmount.multiply(BigDecimal.valueOf(2));
		}
		
		// if self draw win ,and no contract , win amount x3
		if(Constants.ZI_MO.equals(board.getWinType())
				&& StringUtils.isEmpty(board.getContractOne())
				&&StringUtils.isEmpty(board.getContractTwo())
				&&StringUtils.isEmpty(board.getContractThree())){
			finalWinAmount = finalWinAmount.multiply(BigDecimal.valueOf(3));
		}
		
		board.setWinAmount(finalWinAmount);
		
		return board;
	}

	private void setLoserandAmount(Board board){
		StringBuilder loserBuilder = new StringBuilder();
		if(Constants.DIAN_PAO.equals(board.getWinType())){
		if(Constants.PLAYER_ONE.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerOne(board.getGame_id()));			
			board.setPlayerOneStake(board.getWinAmount().negate());
			board.setPlayerTwoStake(BigDecimal.ZERO);
			board.setPlayerThreeStake(BigDecimal.ZERO);
			board.setPlayerFourStake(BigDecimal.ZERO);
		}
		else if(Constants.PLAYER_TWO.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerTwo(board.getGame_id()));
			board.setPlayerTwoStake(board.getWinAmount().negate());
			board.setPlayerOneStake(BigDecimal.ZERO);
			board.setPlayerThreeStake(BigDecimal.ZERO);
			board.setPlayerFourStake(BigDecimal.ZERO);
		}
		else if(Constants.PLAYER_THREE.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerThree(board.getGame_id()));
			board.setPlayerThreeStake(board.getWinAmount().negate());
			board.setPlayerOneStake(BigDecimal.ZERO);
			board.setPlayerTwoStake(BigDecimal.ZERO);
			board.setPlayerFourStake(BigDecimal.ZERO);
		}
		else{
			board.setLoser(gameDao.findPlayerFour(board.getGame_id()));
			board.setPlayerFourStake(board.getWinAmount().negate());
			board.setPlayerOneStake(BigDecimal.ZERO);
			board.setPlayerThreeStake(BigDecimal.ZERO);
			board.setPlayerTwoStake(BigDecimal.ZERO);
		}
		}
		else{
			String PlayerOneName =gameDao.findPlayerOne(board.getGame_id());
			String PlayerTwoName =gameDao.findPlayerTwo(board.getGame_id());
			String PlayerThreeName =gameDao.findPlayerThree(board.getGame_id());
			String PlayerFourName =gameDao.findPlayerFour(board.getGame_id());
			if(Constants.PLAYER_ONE.equals(board.getWinner())){
				loserBuilder.append(PlayerTwoName).append(Constants.SEMICOLON).append(PlayerThreeName).append(Constants.SEMICOLON)
				.append(PlayerFourName);
			}
			else if(Constants.PLAYER_TWO.equals(board.getWinner())){
				loserBuilder.append(PlayerOneName).append(Constants.SEMICOLON).append(PlayerThreeName).append(Constants.SEMICOLON)
				.append(PlayerFourName);
			}
			else if(Constants.PLAYER_THREE.equals(board.getWinner())){
				loserBuilder.append(PlayerOneName).append(Constants.SEMICOLON).append(PlayerTwoName).append(Constants.SEMICOLON)
				.append(PlayerFourName);
			}
			else{
				loserBuilder.append(PlayerOneName).append(Constants.SEMICOLON).append(PlayerTwoName).append(Constants.SEMICOLON)
				.append(PlayerThreeName);
			}
			board.setLoser(loserBuilder.toString());
			
		}
	}
	
	private void populatePoolAmount(Board board){
		//if contract , it need to multiple the win amount
		if(!StringUtils.isEmpty(board.getContractOne())|| !StringUtils.isEmpty(board.getContractTwo()) ||!StringUtils.isEmpty(board.getContractThree())){
			calcContract(board);
		}
		//normal winAmount calculation process
		else{
		if(Constants.PLAYER_ONE.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerOne(board.getGame_id()));						
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				
			}else{
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
			setLoserandAmount(board);
			board.setPlayerOneStake(board.getWinAmount());
		}
		else if(Constants.PLAYER_TWO.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerTwo(board.getGame_id()));
			
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
			setLoserandAmount(board);
			board.setPlayerTwoStake(board.getWinAmount());
		}
		else if(Constants.PLAYER_THREE.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerThree(board.getGame_id()));
			
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
			setLoserandAmount(board );
			board.setPlayerThreeStake(board.getWinAmount());
		}
		else{
			board.setWinner(gameDao.findPlayerFour(board.getGame_id()));
			
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
			setLoserandAmount(board);
			board.setPlayerFourStake(board.getWinAmount());
		}
		}
	}
	
	private void calcContract(Board board){
		             BigDecimal winAmountBeforeContract = board.getWinAmount();		
		             BigDecimal contractLoseAmount =BigDecimal.ZERO;
		             BigDecimal contractWinAmount = BigDecimal.ZERO;
		             int contractCount =0;
					if(!StringUtils.isEmpty(board.getContractOne())|| !StringUtils.isEmpty(board.getContractTwo()) ||!StringUtils.isEmpty(board.getContractThree())){
						if(Constants.ZI_MO.equals(board.getWinType())){		
							contractLoseAmount = winAmountBeforeContract.multiply(BigDecimal.valueOf(5));	
						if(Constants.PLAYER_ONE.equals(board.getContractOne())
								||Constants.PLAYER_ONE.equals(board.getContractTwo())
								||Constants.PLAYER_ONE.equals(board.getContractThree())){
							contractCount++;
							board.setPlayerOneStake(contractLoseAmount.negate());
						}
						 if(Constants.PLAYER_TWO.equals(board.getContractOne())
								||Constants.PLAYER_TWO.equals(board.getContractTwo())
								||Constants.PLAYER_TWO.equals(board.getContractThree())){
							contractCount++;
							board.setPlayerTwoStake(contractLoseAmount.negate());
						}
						 if(Constants.PLAYER_THREE.equals(board.getContractOne())
								||Constants.PLAYER_THREE.equals(board.getContractTwo())
								||Constants.PLAYER_THREE.equals(board.getContractThree())){
							contractCount++;
							board.setPlayerThreeStake(contractLoseAmount.negate());
						}
						 if(Constants.PLAYER_FOUR.equals(board.getContractOne())
								||Constants.PLAYER_FOUR.equals(board.getContractTwo())
								||Constants.PLAYER_FOUR.equals(board.getContractThree())){
							contractCount++;
							board.setPlayerFourStake(contractLoseAmount.negate());
						}													
				}				
				else{
					contractLoseAmount =winAmountBeforeContract.multiply(BigDecimal.valueOf(2));	
					if(Constants.PLAYER_ONE.equals(board.getContractOne())
							||Constants.PLAYER_ONE.equals(board.getContractTwo())
							||Constants.PLAYER_ONE.equals(board.getContractThree())){
						contractCount++;
						board.setPlayerOneStake(contractLoseAmount.negate());
					}
					if(Constants.PLAYER_TWO.equals(board.getContractOne())
							||Constants.PLAYER_TWO.equals(board.getContractTwo())
							||Constants.PLAYER_TWO.equals(board.getContractThree())){
						contractCount++;
						board.setPlayerTwoStake(contractLoseAmount.negate());
					}
					 if(Constants.PLAYER_THREE.equals(board.getContractOne())
							||Constants.PLAYER_THREE.equals(board.getContractTwo())
							||Constants.PLAYER_THREE.equals(board.getContractThree())){
						contractCount++;
						board.setPlayerThreeStake(contractLoseAmount.negate());
					}
					 if(Constants.PLAYER_FOUR.equals(board.getContractOne())
							||Constants.PLAYER_FOUR.equals(board.getContractTwo())
							||Constants.PLAYER_FOUR.equals(board.getContractThree())){
						contractCount++;
						board.setPlayerFourStake(contractLoseAmount.negate());
					}	
				}	
					}
					
					// final contract win
					for(int i=0 ;i<contractCount;i++){
						contractWinAmount = contractWinAmount.add(contractLoseAmount);
					}
					board.setWinAmount(contractWinAmount);
	}

	private void constructComments(Board board){
		StringBuilder commentsBuilder = new StringBuilder();
		if(StringUtils.isEmpty(board.getWinner())){
		commentsBuilder.append(Constants.HUANG_FAN);		
		}
		else{
		commentsBuilder.append(board.getWinType()).append(Constants.SEMICOLON)
		.append(board.getHandType()).append(Constants.SEMICOLON);
		}
		board.setComment(commentsBuilder.toString());
	}
	
	private void updateNoWinDraw(Board board){
		Game currentGame =gameDao.findById(board.getGame_id());
		if(currentGame.getDrawCount()>0){
			int noWinDrawCount = currentGame.getDrawCount();
			if(StringUtils.isEmpty(board.getWinner())){
				currentGame.setDrawCount(noWinDrawCount+1);
			}
			else{
				currentGame.setDrawCount(noWinDrawCount-1);
			}			
		}
		gameDao.update(currentGame);
	}
	
	@Override
	public int countBoards(int gameId){
		return boardDao.countBoardsByGameId(gameId);
	}
}
