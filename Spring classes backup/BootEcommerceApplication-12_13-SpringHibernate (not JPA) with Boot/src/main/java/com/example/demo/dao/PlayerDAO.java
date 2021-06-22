package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Player;

@Repository
@Transactional(rollbackFor = {SQLException.class})
public class PlayerDAO {

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();//session factory created in the Hibernateconfig class
		return session;
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	// replica of what we have in PlayerService
	public boolean save(Player player) throws Exception {
		Session session = getSession();
		
		Integer i = (Integer)session.save(player);
		throw new SQLException("DEMO TRX management...!");
		/*
		 * Transaction tx = null; Integer i = 0; try { tx = session.beginTransaction();
		 * i = (Integer)session.save(player); //i = (Integer)session.save(player); //i =
		 * (Integer)session.save(player); throw new
		 * Exception("DEMO TRX management...!");
		 * 
		 * } catch (Exception e) { if (tx!=null) tx.rollback(); e.printStackTrace();
		 * //throw e; } tx.commit();
		 */
		//if(i>=1) return true; else return false;
		
		// Spring data JPA will provide readymade methods. JPA repository, CRUD repsitory, MANGO repository. The logics will be writted in Spring JPA's. Relevent to the entity it will automatically run the query and return data. we need @Repository for this. Even in Spring JPA's if you want your own Hibernate queries you can write HQL,Namedqueries or Creteria queries.
		// we are not using any transaction here like hibernate (we are using @Transactional-Spring integrated with hibernate)
		//session.close();// using same session for all methods
		
		
		//just check in which cases we will get HQL in sysout and normal SQL in sysouts - ??

		/*
		 * i = (Integer)session.save(player); i = (Integer)session.save(player); throw
		 * new Exception("DEMO TRX management...!");
		 */
		/*
		 * if(i>=1) return true; else return false;
		 */
	}
	
	public List<Player> findAll() {
		Session session = getSession();
		
		Query query = session.createQuery("from Player", Player.class);
		
		List<Player> players = query.getResultList();
		
		return players;
	}
	
	public Player findOne() {
		
		// all these manual codings(hibernate)
		Session session = getSession();
		
		Query query = session.createQuery("from Player", Player.class);
		query.setFirstResult(1);
		List<Player> players = query.getResultList();
		Player player = (Player)players.get(0);
		return player;
	}
	
	public Player findByPlayerId(int playerId) {
		
		Session session = getSession();
		
		Query query = session.createQuery("from Player p where p.playerId=:playerId", Player.class);
		
		query.setParameter("playerId", playerId);
		
		Player player = (Player)query.getSingleResult();
		
		return player; 
	}
	 
	public List<Player> findByPlayerName(String playerName) {
		
		Session session = getSession();

		//here we can use Native query or creteria query as well
		Query query = session.createQuery("from Player p where p.playerName=:playerName", Player.class);
		
		query.setParameter("playerName", playerName);
		
		List<Player> players = query.getResultList();
		
		return players;
	}
	
	public boolean update(int playerId, float playerAverageScore) {
		//This is Spring with Hibernate coding(Session and transaction manager is from spring data jpa jar but this is Spring with hibernate)
		Session session = getSession();
		Player player = (Player)session.get(Player.class, playerId);
		boolean result= false; 
		if(player != null)
		{
			player.setPlayerAverageScore(playerAverageScore);// for the given player of the id, update the avg score 
			session.update(player);
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}

	public boolean remove(int playerId) {
		
		Session session = getSession();
		Player player = (Player)session.get(Player.class, playerId);
		boolean result= false; 
		if(player != null)
		{
			session.remove(player);
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
	
}
