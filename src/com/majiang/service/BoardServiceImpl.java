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
	public void saveOrUpdate(Board board) {
		if (findByBoardId(board.getId())==null) {
			boardDao.save(board);
		} else {
			boardDao.update(board);
		}

		
	}
	
	@Override
	public void saveGameBoard(Board board){
		if(!StringUtils.isEmpty(board.getWinner())){
		CalulateAmount(board);
		populatePoolAmount(board);
		board.setBoardDate(new Date());
		board.setComment("Bac");
		boardDao.save(board);
		//save each player win
		}else{
			//no win draw
			}		
	}

	@Override
	public Board CalulateAmount(Board board) {
			
		Game currentGame = gameDao.findById(board.getGame_id());
		BigDecimal baseAmount = currentGame.getBaseAmount();
		BigDecimal flowerAmount = currentGame.getFlowerAmount();
		int maxFlowers = currentGame.getMaxFlowers();
		String handType = board.getHandType();
		String winType=board.getWinType();
		BigDecimal laziWinAmount = baseAmount.add(flowerAmount.multiply(BigDecimal.valueOf(maxFlowers)));
		BigDecimal FinalWinAmount ;
		if(Constants.HUN_YI_SE.equals(handType)||Constants.PENG_PENG_HU.equals(handType)){
			FinalWinAmount = baseAmount.add(flowerAmount.multiply(BigDecimal.valueOf(board.getFlowers())));			
		}
		else if(Constants.QING_YI_SE.equals(handType)||Constants.HUN_PENG.equals(handType)||Constants.DA_DIAO_CHE.equals(handType)){
			FinalWinAmount = laziWinAmount;
		}
		else if(Constants.QUAN_FENG_XIANG.equals(handType) || Constants.QING_PENG.equals(handType)){
			FinalWinAmount = laziWinAmount.multiply(BigDecimal.valueOf(2));
		}
		else{
			FinalWinAmount = laziWinAmount.multiply(BigDecimal.valueOf(4));
		}
		
		//clear door double the win amount
		if(board.getClearDoor()){
			FinalWinAmount = FinalWinAmount.multiply(BigDecimal.valueOf(2));
		}
		
		// if no win, double again
		Game game = gameDao.findById(board.getGame_id());
		if(game.getDrawCount()!=0){
			FinalWinAmount = FinalWinAmount.multiply(BigDecimal.valueOf(2));
		}
		
		// contract calc
		if(Constants.ZI_MO.equals(winType)){		
			if(!StringUtils.isEmpty(board.getContractOne())){
				board.setWinAmount(FinalWinAmount.multiply(BigDecimal.valueOf(5)));				
			}
			else{
				board.setWinAmount(FinalWinAmount.multiply(BigDecimal.valueOf(3)));
			}
		}
		else{
			if(!StringUtils.isEmpty(board.getContractOne())){
				board.setWinAmount(FinalWinAmount.multiply(BigDecimal.valueOf(2)));				
			}
			else{
				board.setWinAmount(FinalWinAmount);
			}
		}	
		return board;
	}

	private void setLoserandAmount(Board board){
		if(Constants.PLAYER_ONE.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerOne(board.getGame_id()));			
			board.setPlayerOneStake(board.getWinAmount().negate());
		}
		else if(Constants.PLAYER_TWO.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerTwo(board.getGame_id()));
			board.setPlayerTwoStake(board.getWinAmount().negate());
		}
		else if(Constants.PLAYER_THREE.equals(board.getLoser())){
			board.setLoser(gameDao.findPlayerThree(board.getGame_id()));
			board.setPlayerThreeStake(board.getWinAmount().negate());
		}
		else{
			board.setLoser(gameDao.findPlayerFour(board.getGame_id()));
			board.setPlayerFourStake(board.getWinAmount().negate());
		}
	}
	
	private void populatePoolAmount(Board board){
		if(Constants.PLAYER_ONE.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerOne(board.getGame_id()));			
			board.setPlayerOneStake(board.getWinAmount());
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				setLoserandAmount(board);
			}else{
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
		}
		else if(Constants.PLAYER_TWO.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerTwo(board.getGame_id()));
			board.setPlayerTwoStake(board.getWinAmount());
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				setLoserandAmount(board);
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
		}
		else if(Constants.PLAYER_THREE.equals(board.getWinner())){
			board.setWinner(gameDao.findPlayerThree(board.getGame_id()));
			board.setPlayerThreeStake(board.getWinAmount());
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				setLoserandAmount(board );
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerFourStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
		}
		else{
			board.setWinner(gameDao.findPlayerFour(board.getGame_id()));
			board.setPlayerFourStake(board.getWinAmount());
			if(Constants.DIAN_PAO.equals(board.getWinType())){
				setLoserandAmount(board);
			}else{
				board.setPlayerOneStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerTwoStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
				board.setPlayerThreeStake(board.getWinAmount().divide(BigDecimal.valueOf(3)).negate());
			}
		}
	}

}
