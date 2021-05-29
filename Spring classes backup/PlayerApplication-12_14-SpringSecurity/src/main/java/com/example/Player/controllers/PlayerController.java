package com.example.Player.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Player.entities.Player;
import com.example.Player.service.PlayerService;



@RestController
public class PlayerController {
//Imp - controller service DAO should be there for every Entity(Table)
	@Autowired
	PlayerService playerService;
	
	
	
	@GetMapping("/welcome")
	public String getWelcomeMessage() {
		
/*		Authentication auth;//spring based authinfo
		auth.getName();*/ // same as return principal.getName();
		return "Welcome to Player Portal";
		
	}
	
	@GetMapping("/userinfo")
	public String getuserInfo(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();//java based based authinfo
		//return "Hi User Manoj. Welcome";
		return principal.getName();
	}
	
	@PostMapping("/savePlayer")
	public boolean savePlayer(@RequestBody Player player) {
		return playerService.save(player);
		
	}
	
	@GetMapping("/allplayers")
	public List<Player> getAllPlayers() {
		return playerService.findAll();
	}
	
/*	@GetMapping("/oneplayer")
	public Player getOnePlayer() {
		return playerService.fineOne();
	}*/
	
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
