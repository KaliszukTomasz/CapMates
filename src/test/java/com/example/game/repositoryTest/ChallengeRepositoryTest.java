package com.example.game.repositoryTest;

import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;

import org.assertj.core.api.InstantAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Challenge;
import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.PlayerServiceImpl;

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
	public void shouldTakeAllChallengeHistoryOfOnePlayer() {
		// given
		assertTrue(4 == challengeRepositoryImpl.getPlayerAllChallengeHistory(0L).size());
		Challenge challenge = new Challenge(0L, LocalDateTime.now(), "someGame", 0L, 1);

		// when
		challengeRepositoryImpl.addChallenge(challenge);

		// then
		assertTrue(5 == challengeRepositoryImpl.getPlayerAllChallengeHistory(0L).size());
		challengeRepositoryImpl.getChallengeList().remove(challenge);
		assertTrue(4 == challengeRepositoryImpl.getPlayerAllChallengeHistory(0L).size());
	}

	@Test
	public void shouldTakeChallengeHistoryOfOnePlayerInOneGame() {
		//given
		Challenge challenge = new Challenge(0L, LocalDateTime.now(), "Chess", 0L, 1);
		assertTrue(3 == challengeRepositoryImpl.getPlayerOneGameChallengeHistory(0L, "Chess").size());
		
		//when
		challengeRepositoryImpl.addChallenge(challenge);
		//then
		assertTrue(4 == challengeRepositoryImpl.getPlayerOneGameChallengeHistory(0L, "Chess").size());
		challengeRepositoryImpl.getChallengeList().remove(challenge);
		assertTrue(3 == challengeRepositoryImpl.getPlayerOneGameChallengeHistory(0L, "Chess").size());
	}
}
