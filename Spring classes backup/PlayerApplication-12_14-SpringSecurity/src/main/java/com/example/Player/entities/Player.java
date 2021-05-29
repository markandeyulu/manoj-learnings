package com.example.Player.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@Entity ////Imp - controller, service & DAO should be there for every Entity(Table) - thats the standard way
@NamedNativeQuery(name="updatequery", query = "update player set player_average_score=:score where player_id=:id")//oracle will take column name with _. so have that in column names
public class Player {

	/*
	#Flow to connect DB and run queries through JPA
	#1. Enitity class
	#2. Available for Hibernate
	#3. Session Factory
	#4. Session
	#5. CRUD
	
	*/
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int playerId;

		private String playerName;
		private float playerAverageScore;

		public Player() {}
		
		public Player(int playerId, String playerName, float playerAverageScore) {
			super();
			this.playerId = playerId;
			this.playerName = playerName;
			this.playerAverageScore = playerAverageScore;
		}
		
		
		public int getPlayerId() {
			return playerId;
		}

		public void setPlayerId(int playerId) {
			this.playerId = playerId;
		}

		public String getPlayerName() {
			return playerName;
		}

		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}

		public float getPlayerAverageScore() {
			return playerAverageScore;
		}

		public void setPlayerAverageScore(float playerAverageScore) {
			this.playerAverageScore = playerAverageScore;
		}

		
}
