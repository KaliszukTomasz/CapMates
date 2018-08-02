package com.example.game.serviceTest;

import static org.junit.Assert.*;

import com.example.game.transferObjects.RankingTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.ChallengeSeviceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeServiceTest {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	@Autowired
	ChallengeRepositoryImpl challengeRepositoryImpl;

	@Autowired
	ChallengeSeviceImpl challengeServiceImpl;

	@Test
	public void shouldShowRankingPointsFromOnePlayer() {
		// given
		// when
		RankingTO ranking = challengeServiceImpl.getPlayerRankingInOneGame(0L, "Chess");
		RankingTO ranking2 = challengeServiceImpl.getPlayerRankingInOneGame(0L, "Monopoly");

		// then
		assertTrue(21 == ranking.getPoints());
		assertTrue(10 == ranking2.getPoints());
	}

	@Test
	public void shouldShowRankingPositionOnePlayerInOneGame() {
		// given
		// when
		int rankingPosition = challengeServiceImpl.findPlayerPositionInRankingInOneGame(0L, "Chess");
		// then
		assertEquals(1, rankingPosition);
	}

	@Test
	public void shouldShowRankingSecoundPositionOnePlayerInOneGame() {
		// given
		// when
		int rankingPosition = challengeServiceImpl.findPlayerPositionInRankingInOneGame(1L, "Chess");
		// then
		assertEquals(2, rankingPosition);
	}

}
