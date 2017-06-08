package com.majiang.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.majiang.model.Board;

@Repository
@Transactional
public class BoardDaoImpl implements BoardDao {

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
	public List<Board> findAllBoards() {
		Criteria criteria = getSession().createCriteria(Board.class);
		List<Board> boards = (List<Board>) criteria.list();
        return boards;
	}



	@Override
	public Board findByBoardId(int id) {
		Criteria criteria = getSession().createCriteria(Board.class);
        criteria.add(Restrictions.eq("id",id));
        return (Board) criteria.uniqueResult();
	}
	
	@Override
	public void save(Board board)	{		
		 getSession().persist(board);
		 getSession().flush();
		 getSession().clear();
	}

	@Override
	public void update(Board board){
		 getSession().update(board);
	}

}
