package com.majiang.service;

import java.util.List;

import com.majiang.model.Player;


public interface PlayerService {

	Player findById(int id);
	
	List<Player> findAll();

	void saveOrUpdate(Player player);
	
	void delete(int id);
	

	
}