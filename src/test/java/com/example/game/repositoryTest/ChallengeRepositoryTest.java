package com.example.game.repositoryTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeRepositoryTest {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	@Autowired
	ChallengeRepositoryImpl challengeRepositoryImpl;
	
	@Test
	public void shouldTakeAllChallengeHistoryOfOnePlayer(){
		assertTrue(4 == challengeRepositoryImpl.getPlayerAllChallengeHistory(0L).size());
		
		
	}
	
	@Test
	public void shouldTakeChallengeHistoryOfOnePlayerInOneGame(){
		assertTrue(3 == challengeRepositoryImpl.getPlayerOneGameChallengeHistory(0L, "Chess").size());
		
	}
}
