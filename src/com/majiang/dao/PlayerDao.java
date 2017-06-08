package com.majiang.dao;

import java.util.List;

import com.majiang.model.Player;


public interface PlayerDao {

	Player findById(Integer id);

	List<Player> findAll();

	void save(Player player);

	void update(Player player);

	void delete(int id);

}