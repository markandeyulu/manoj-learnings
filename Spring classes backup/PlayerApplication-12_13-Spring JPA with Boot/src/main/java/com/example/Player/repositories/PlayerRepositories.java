package com.example.Player.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Player.entities.Player;

public interface PlayerRepositories extends JpaRepository<Player, Integer>{

	/*
	 * 
	 * 
	JPARepository - has all (it has PagingAndSortingRepo) - This is the best to use since it has all
	CRUDRepository
	PagingAndSortingRepository(internally uses CRUD)
	
	*JPA has the CRUD and Paging and Sorting repo. and it has the hibernate operations as well.
	*/
	
	// This interface has most of the basic operations
	
	//Naming convention is imp - you should have field called "PlayerName"
	public List<Player> findByPlayerName(String playerName);//just we need to keep abstract. we dont need to write implementation here. By name it will take and do the job for us. Runtime implementation will write code  for us. adv of JPA.
	
	//learn about @Modifying annotation
	// we just need to abstract the method we need in the interface so w can achieve the operations which is not supported by the JPA by default
	@Query(name="updatequery")// This is JPA query annotation // name= is imp
	public void update(@Param("id") int playerId, @Param("score") float playerAverageScore);
	
	
}
