package com.example.Player.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Player.entities.Player;
import com.example.Player.repositories.PlayerRepositories;


@Repository
//@Transactional - not needed for JPA
public class PlayerDAO {

/*	Not needed in JPA approach
 * private Session getSession() {
		Session session = sessionFactory.getCurrentSession();//session factory created in the Hibernateconfig class
		return session;
	}
	
	@Autowired
	SessionFactory sessionFactory;
	*/
	// replica of what we have in PlayerService
	
	@Autowired
	PlayerRepositories playerRepositories;
	
	public boolean save(Player player) {
		
		Player players = playerRepositories.save(player);
		
		if(player != null)
		{
			return true;	
		}
		else
		{
			return false;
		}
		
	}
	
	public List<Player> findAll() {
		
		return playerRepositories.findAll();
	}
	
/*	public Player findOne() {
		
		return playerRepositories.f
	}*/
	
	public Player findByPlayerId(int playerId) {
		
		Optional<Player> optional = playerRepositories.findById(playerId);//why optional ? - Java 8 - to avoid null pointer. its enabled with null pointer handling 
		//Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value.
		return optional.get();
	}
	 
	public List<Player> findByPlayerName(String playerName) {
		
		return playerRepositories.findByPlayerName(playerName);
	}
	
	public boolean update(int playerId, float playerAverageScore) {
		
		playerRepositories.update(playerId, playerAverageScore);
		return true;
	}

	public boolean remove(int playerId) {
		
		 Optional<Player> optional = playerRepositories.findById(playerId);
		// in hibernateSpring apprach we had remove method. here its diff. compare all methods of JPA with SpringHibernate approaches
		 Player player = optional.get();
		 playerRepositories.delete(player);
		 return true;
	}
	
}
