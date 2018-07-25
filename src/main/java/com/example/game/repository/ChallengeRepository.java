package com.example.game.repository;

import java.util.List;

import com.example.game.entity.Challenge;

public interface ChallengeRepository {

	//Ranking getPlayerRankingInOneGame(Long playerId, Long gameId);

	List<Challenge> getPlayerAllChallengeHistory(Long playerId);

	List<Challenge> getPlayerOneGameChallengeHistory(Long playerId, String gameTitle);
	
	

}
