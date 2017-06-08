package com.majiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majiang.dao.PlayerDao;
import com.majiang.model.Player;


@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerDao playerDao;

	public void setplayerDao(PlayerDao playerDao) {
		this.playerDao = playerDao;
	}

	@Override
	public Player findById(int id) {
		return playerDao.findById(id);
	}

	@Override
	public List<Player> findAll() {
		return playerDao.findAll();
	}

	@Override
	public void saveOrUpdate(Player player) {

		if (findById(player.getId())==null) {
			playerDao.save(player);
		} else {
			playerDao.update(player);
		}

	}

	@Override
	public void delete(int id) {
		playerDao.delete(id);
	}


}