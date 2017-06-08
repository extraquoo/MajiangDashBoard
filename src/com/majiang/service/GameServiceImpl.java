package com.majiang.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.majiang.constant.Constants;
import com.majiang.dao.GameDao;
import com.majiang.model.Game;

@Service("gameService")
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;

	
	
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
}
