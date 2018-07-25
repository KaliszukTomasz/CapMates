package com.example.game.service;

import com.example.game.entity.Ranking;

public interface ChallengeService {

	Ranking getPlayerRankingInOneGame(Long playerId, String gameTitle);
	

}
