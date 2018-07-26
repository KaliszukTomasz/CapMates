package com.example.game.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.entity.Challenge;
import com.example.game.entity.Player;
import com.example.game.entity.Ranking;
import com.example.game.exceptions.NoChallegnesThisGameThisPlayerException;
import com.example.game.repository.ChallengeRepository;
import com.example.game.repository.PlayerRepository;

@Service
public class ChallengeSeviceImpl implements ChallengeService {

	@Autowired
	ChallengeRepository challengeRepository;
	@Autowired
	PlayerRepository playerRepository;

	Integer AMOUNT_POINTS_PER_WIN = 10;
	Integer AMOUNT_POINTS_PER_LOSE = 1;

	@Override
	public Ranking getPlayerRankingInOneGame(Long playerId, String gameTitle) {
		Ranking ranking = new Ranking(0);
		if (challengeRepository.getPlayerOneGameChallengeHistory(playerId, gameTitle) == null) {
			throw new NoChallegnesThisGameThisPlayerException();
		}
		for (Challenge challenge : challengeRepository.getPlayerOneGameChallengeHistory(playerId, gameTitle)) {
			ranking.setPoints(ranking.getPoints() + challenge.getScore());
		}
		return ranking;
	}

	@Override
	public Integer getPlayerPositionInRankingInOneGame(Long playerId, String gameTitle) {
		List<Integer> rankingList = new ArrayList<>();
		for (Player player : playerRepository.getPlayers()) {
			Long id = player.getId();
			try {
				Integer rankingPoints = getPlayerRankingInOneGame(id, gameTitle).getPoints();
				rankingList.add(rankingPoints);
			} catch (NoChallegnesThisGameThisPlayerException e) {
			}
		}
		rankingList.sort((Integer a, Integer b) -> a.compareTo(b));
		Collections.reverse(rankingList);
		Integer rankingPosition = rankingList.indexOf(getPlayerRankingInOneGame(playerId, gameTitle).getPoints());
		return rankingPosition + 1;
	}
}
