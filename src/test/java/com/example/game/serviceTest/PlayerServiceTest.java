package com.example.game.serviceTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.entity.Ranking;
import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.ChallengeSeviceImpl;
import com.example.game.service.PlayerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	@Autowired
	ChallengeRepositoryImpl challengeRepositoryImpl;

	@Autowired
	PlayerServiceImpl playerServiceImpl;

	@Test
	public void shouldAddAndEraseOneGamePlayerCollectionTest() {
		assertEquals(1, playerServiceImpl.getMyGames(0L).size());
		playerServiceImpl.addNewGameToMyGames(0L, "Catan", 4);
		assertEquals(2, playerServiceImpl.getMyGames(0L).size());
		playerServiceImpl.eraseGameFromMyGames(0L, "Catan");
		assertEquals(1, playerServiceImpl.getMyGames(0L).size());

	}
	
	@Test
	public void shouldCheckGetStatisticTest(){
		assertEquals(4 , playerServiceImpl.getMyChallengeHistory(0L).size());
		assertEquals(1, (int)playerServiceImpl.getStatistic(0L).getLostGames());
		assertEquals(3, (int)playerServiceImpl.getStatistic(0L).getWonGames());
				
	}
	
	@Test
	public void should(){
//		playerServiceImpl.getMyProfile(0L);
//		asser
	}

}
