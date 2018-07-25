package com.example.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.entity.Challenge;
import com.example.game.entity.Ranking;
import com.example.game.repository.ChallengeRepository;

@Service
public class ChallengeSeviceImpl implements ChallengeService {

	@Autowired
	ChallengeRepository challengeRepository;

	Integer AMOUNT_POINTS_PER_WIN = 10;
	Integer AMOUNT_POINTS_PER_LOSE = 1;

	@Override
	public Ranking getPlayerRankingInOneGame(Long playerId, String gameTitle) {
		Ranking ranking = new Ranking(0);
		for (Challenge challenge : challengeRepository.getPlayerOneGameChallengeHistory(playerId, gameTitle)) {
			ranking.setPoints(ranking.getPoints() + challenge.getScore());
		}
		return ranking;
	}
}
