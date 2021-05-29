package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Player;
import com.example.demo.services.PlayerService;

@RestController
public class PlayerController {
//Imp - controller service DAO should be there for every Entity(Table)
	@Autowired
	PlayerService playerService;
	
	@PostMapping("/savePlayer")
	public boolean savePlayer(@RequestBody Player player) {
		return playerService.save(player);
		
	}
	
	@GetMapping("/allplayers")
	public List<Player> getAllPlayers() {
		return playerService.findAll();
	}
	
	@GetMapping("/oneplayer")
	public Player getOnePlayer() {
		return playerService.fineOne();
	}
	
	@GetMapping("/playerbyid/{playerId}")
	public Player getPlayersById(@PathVariable int playerId) {
		return playerService.findByPlayerId(playerId);
	}
	
	@GetMapping("/playerbyname/{playerName}")
	public List<Player> getPlayersByName(@PathVariable String playerName) {
		return playerService.findByPlayerName(playerName);
	}
	
	@GetMapping("/updateplayer/{playerId}/{playerAverageScore}") // should it be POST ?
	public String updatePlayer(@PathVariable int playerId, @PathVariable float playerAverageScore) {
		boolean result =  playerService.update(playerId, playerAverageScore);
		String status = "";
		if(result) {
			status="Successfully Updated";
		}
		else {
			status="Failed to Update";
		}
		
		return status;
	}
	
	@GetMapping("/removeplayer/{playerId}")
	public String removePlayer(@PathVariable int playerId) {
		boolean result =  playerService.remove(playerId);
		String status = "";
		if(result) {
			status="Successfully Removed";
		}
		else {
			status="Failed to Remove";
		}
		
		return status;
	}
}
