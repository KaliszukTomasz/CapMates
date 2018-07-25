package com.example.game.serviceTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Ranking;
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
	public void shouldShowRankingPointsFromOnePlayer(){
		Ranking ranking = challengeServiceImpl.getPlayerRankingInOneGame(0L, "Chess");
		assertTrue(21  == ranking.getPoints());
		
		Ranking ranking2 = challengeServiceImpl.getPlayerRankingInOneGame(0L, "Monopoly");
		assertTrue(10  == ranking2.getPoints());
	}
	
	
}
