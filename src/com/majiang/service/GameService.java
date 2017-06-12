package com.majiang.service;

import java.util.List;

import com.majiang.model.Game;
import com.majiang.model.GameBoards;

public interface GameService {

	List<Game> findAllGames();
	
	List<GameBoards> findAllGameBoards();
	
	Game findById(int id);
	
	void saveOrUpdate(Game game);
	
	String findPlayer(String playerholder, int id);
	
	void endGame(int id);
	
	void startGame(int id);
}
