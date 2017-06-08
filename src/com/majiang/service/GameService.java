package com.majiang.service;

import java.util.List;

import com.majiang.model.Game;

public interface GameService {

	List<Game> findAllGames();
	
	Game findById(int id);
	
	void saveOrUpdate(Game game);
	
	String findPlayer(String playerholder, int id);
}
