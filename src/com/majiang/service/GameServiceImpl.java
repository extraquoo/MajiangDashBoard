package com.majiang.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majiang.constant.Constants;
import com.majiang.dao.BoardDao;
import com.majiang.dao.GameDao;
import com.majiang.model.Game;
import com.majiang.model.GameBoards;

@Service("gameService")
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;

	@Autowired
	BoardDao boardDao;
	
	public GameDao getGameDao() {
		return gameDao;
	}


	@Override
	public List<Game> findAllGames() {
		return gameDao.findAllGames();
	}


	@Override
	public Game findById(int id) {	
		return gameDao.findById(id);
	}

	@Override
	public void saveOrUpdate(Game game) {

		if (findById(game.getId())==null) {
			game.setStartDate(new Date());
			gameDao.save(game);
		} else {
			gameDao.update(game);
		}

	}
	
	@Override
	public String findPlayer(String player, int id) {
		String name ="";
		if(Constants.PLAYER_ONE.equals(player)){
			name = gameDao.findPlayerOne(id);
		}
		else if(Constants.PLAYER_TWO.equals(player)){
			name = gameDao.findPlayerTwo(id);
		}
		else if(Constants.PLAYER_THREE.equals(player)){
			name = gameDao.findPlayerThree(id);
		}
		else {
			name = gameDao.findPlayerFour(id);
		}
		return name;
	}
	
	@Override
	public List<GameBoards> findAllGameBoards(){
		List<Game> games = findAllGames();
		List<GameBoards> gameBoards = new ArrayList<GameBoards>();
		for(Game game: games){
			GameBoards gameBoard = new GameBoards();
			gameBoard.setId(game.getId());
			gameBoard.setPlayerOne(game.getPlayerOne());
			gameBoard.setPlayerTwo(game.getPlayerTwo());
			gameBoard.setPlayerThree(game.getPlayerThree());
			gameBoard.setPlayerFour(game.getPlayerFour());
			gameBoard.setCountBoards(boardDao.countBoardsByGameId(game.getId()));
			gameBoard.setStartDate(game.getStartDate());
			gameBoard.setEndDate(game.getEndDate());
			gameBoards.add(gameBoard);
		}
		return gameBoards;
	}
	
	@Override
	public void endGame(int id){
		gameDao.endGame(id);
	}
	
	@Override
	public void startGame(int id){
		gameDao.startGame(id);
	}
}
