package com.example.game.service;

import com.example.game.entity.Ranking;
import com.example.game.transferObjects.RankingTO;

public interface ChallengeService {

	RankingTO getPlayerRankingInOneGame(Long playerId, String gameTitle);

	Integer findPlayerPositionInRankingInOneGame(Long playerId, String gameTitle);

}
