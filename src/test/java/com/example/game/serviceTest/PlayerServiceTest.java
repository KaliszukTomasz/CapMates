package com.example.game.serviceTest;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.assertj.core.api.InstantAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Ranking;
import com.example.game.exceptions.PlayerAvailabilityListIsEmptyException;
import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.ChallengeSeviceImpl;
import com.example.game.service.PlayerServiceImpl;
import com.example.game.validators.AvailabilityTimeValidator;

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
	public void shouldCheckGetStatisticTest() {
		assertEquals(4, playerServiceImpl.getMyChallengeHistory(0L).size());
		assertEquals(1, (int) playerServiceImpl.getStatistic(0L).getLostGames());
		assertEquals(3, (int) playerServiceImpl.getStatistic(0L).getWonGames());

	}

	
	@Test
	public void shouldAddNewHoursAndEraseToAvailabilityPlayer() {
		PlayerAvailability availabilityTime = new PlayerAvailability();
		availabilityTime.setStartTime(Instant.now().plusSeconds(3600));
		availabilityTime.setEndTime(Instant.now().plusSeconds(7200));
		assertEquals(1, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());
		playerServiceImpl.addMyAvailabilityTime(0L, availabilityTime);
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());
		playerServiceImpl.eraseMyAvailabilityTime(0L, availabilityTime);
		assertEquals(1, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());

	}

	@Test(expected = PlayerAvailabilityListIsEmptyException.class)
	public void shouldThrowExceptionPlayerAvailabilityListIsEmpty() {

		assertEquals(0, playerRepositoryImpl.getPlayer(1L).getPlayerAvailabilityList().size());

	}
}
