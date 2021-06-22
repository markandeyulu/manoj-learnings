package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PlayerDAO;
import com.example.demo.entities.Player;

@Service // why we are not using component here? - we can use that as well. But absolute is service.
public class PlayerService {

	@Autowired
	PlayerDAO playerDAO;
	
	//CRUD operations
	public boolean save(Player player) {
		try {
			return playerDAO.save(player);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Player> findAll() {
		return playerDAO.findAll();
	}
	
	public Player fineOne() {
		return playerDAO.findOne();
	}
	
	public Player findByPlayerId(int playerId) {
		return playerDAO.findByPlayerId(playerId);	
	}
	 
	public List<Player> findByPlayerName(String playerName) {
		return playerDAO.findByPlayerName(playerName);
	}
	
	public boolean update(int playerId, float playerAverageScore) {
		return playerDAO.update(playerId, playerAverageScore);
	}

	public boolean remove(int playerId) {
		return playerDAO.remove(playerId);
	}
}
