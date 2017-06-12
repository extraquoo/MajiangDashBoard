package com.majiang.dao;

import java.util.List;

import com.majiang.model.Game;

public interface GameDao {

	List<Game> findAllGames();
	
	Game findById(int id);
	
	void save(Game game);

	void update(Game game);
	
	String findPlayerOne (int id);
	
	String findPlayerTwo (int id);
	
	String findPlayerThree (int id);
	
	String findPlayerFour (int id);
	
	void endGame(int id);
	
	void startGame(int id);
	
}
