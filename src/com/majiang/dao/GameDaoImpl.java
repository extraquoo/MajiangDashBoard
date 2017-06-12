package com.majiang.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.majiang.model.Game;

@Repository
@Transactional
public class GameDaoImpl  implements GameDao{

    private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
    


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findAllGames() {
		Criteria criteria = getSession().createCriteria(Game.class);
		List<Game> Games = (List<Game>) criteria.list();
        return Games;
	}



	@Override
	public Game findById(int id) {
		Criteria criteria = getSession().createCriteria(Game.class);
        criteria.add(Restrictions.eq("id",id));
        return (Game) criteria.uniqueResult();
	}
	
	@Override
	public void save(Game game)	{		
		 getSession().persist(game);
	}

	@Override
	public void update(Game game){
		 getSession().update(game);
	}

	
	public String findPlayerOne(int id){
		 Query query = getSession().getNamedQuery("findPlayerOne");  
		 query.setInteger("id", id);  
		 Game game = (Game)query.uniqueResult();	
		return game.getPlayerOne();	
	}
	
	public String findPlayerTwo(int id){
		 Query query = getSession().getNamedQuery("findPlayerTwo");  
		 query.setInteger("id", id);  
		 Game game = (Game)query.uniqueResult();	
		return game.getPlayerTwo();	
	}
	
	public String findPlayerThree(int id){
		 Query query = getSession().getNamedQuery("findPlayerThree");  
		 query.setInteger("id", id);  
		 Game game = (Game)query.uniqueResult();		
		return game.getPlayerThree();	
	}
	
	public String findPlayerFour(int id){
		 Query query = getSession().getNamedQuery("findPlayerFour");  
		 query.setInteger("id", id);  
		 Game game = (Game)query.uniqueResult();	
		return game.getPlayerFour();	
	}
	
	@Override
	public void startGame(int id){
		 Query query = getSession().getNamedQuery("startGame");  
		 query.setDate("startDate", new Date());
		 query.setInteger("id", id);  
		 query.executeUpdate();
	}
	
	@Override
	public void endGame(int id){
		 Query query = getSession().getNamedQuery("endGame");  
		 query.setDate("endDate", new Date());
		 query.setInteger("id", id);  
		 query.executeUpdate();
	}
}
