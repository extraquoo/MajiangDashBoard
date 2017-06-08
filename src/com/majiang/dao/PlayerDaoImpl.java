package com.majiang.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.majiang.model.Player;
import com.majiang.model.Wallet;


@Repository
@Transactional
public class PlayerDaoImpl  implements PlayerDao {

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
	
	
	@Override
	public void save(Player player){
		 player.setWallet(new Wallet());
		 getSession().persist(player);
	}

	@Override
	public void update(Player player){
		 getSession().update(player);
	}

	@Override
	public void delete(int id){
		Player player = (Player) getSession().load(Player.class, id);
		getSession().delete(player);
	}

	@Override
	public Player findById(Integer id) {
		Criteria criteria = getSession().createCriteria(Player.class);
        criteria.add(Restrictions.eq("id",id));
        return (Player) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Player> findAll() {
		Criteria criteria = getSession().createCriteria(Player.class);
		List<Player> players = (List<Player>) criteria.list();
        return players;
	}


}