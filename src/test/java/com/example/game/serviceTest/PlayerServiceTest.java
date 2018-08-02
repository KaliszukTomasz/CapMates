package com.example.game.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDateTime;

import com.example.game.transferObjects.PlayerProfileBuilder;
import com.example.game.transferObjects.PlayerQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.enums.Status;
import com.example.game.entity.Challenge;
import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.PlayerServiceImpl;
import com.example.game.transferObjects.AvailabilityTimeTO;
import com.example.game.transferObjects.PlayerProfile;

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
	public void shouldEditPlayerProfileTest() {
		// Given
		PlayerProfile playerProfile = playerServiceImpl.getMyProfile(2L);
		// When
		playerServiceImpl.editMyProfile(2L, new PlayerQuery());
		// Then
		assertThat(playerProfile).isNotEqualTo(playerServiceImpl.getMyProfile(2L));

	}

	@Test
	public void shouldHideOurPasswordInPlayerProfileTest() {
		// Given

		// When
		PlayerProfile playerProfil = playerServiceImpl.getMyProfile(1L);

		// Then
		assertTrue(playerProfil.getPassword() == null);

	}

	@Test
	public void shouldTakeListOfChallengeTOTest() {
		// Given
		challengeRepositoryImpl.addChallenge(new Challenge(10L, LocalDateTime.now(), "monopol01", 5L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(11L, LocalDateTime.now(), "monopol02", 5L, 1));
		challengeRepositoryImpl.addChallenge(new Challenge(12L, LocalDateTime.now(), "monopol03", 5L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(13L, LocalDateTime.now(), "monopol04", 5L, 10));
		// When

		// Then
		assertEquals(4, (int) playerServiceImpl.getMyChallengeHistory(5L).size());
		assertEquals("monopol02", playerServiceImpl.getMyChallengeHistory(5L).get(1).getGameTitle());
		assertTrue(5L == playerServiceImpl.getMyChallengeHistory(5L).get(1).getPlayerId());
		assertTrue(1 == playerServiceImpl.getMyChallengeHistory(5L).get(1).getScore());
	}

	@Test
	public void shouldTakeActualPlayerLevelTest() {
		// Given
		challengeRepositoryImpl.addChallenge(new Challenge(10L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(11L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(12L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(13L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(14L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(15L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(16L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(17L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(18L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(19L, LocalDateTime.now(), "monopol", 4L, 10));
		challengeRepositoryImpl.addChallenge(new Challenge(20L, LocalDateTime.now(), "monopol", 4L, 10));
		// When

		// Then
		assertEquals(2, (int) playerServiceImpl.getMyLevel(4L));
		assertEquals(1, (int) playerServiceImpl.getMyLevel(0L));

	}

	@Test
	public void shouldAddAndEraseOneGamePlayerCollectionTest() {
		// Given

		// When

		// Then
		assertEquals(2, playerServiceImpl.getMyGames(0L).size());
		playerServiceImpl.addNewGameToMyGames(0L, "Catan", 4);
		assertEquals(3, playerServiceImpl.getMyGames(0L).size());
		playerServiceImpl.eraseGameFromMyGames(0L, "Catan");
		assertEquals(2, playerServiceImpl.getMyGames(0L).size());

	}

	@Test
	public void shouldCheckGetStatisticTest() {
		// Given

		// When

		// Then
		assertEquals(4, playerServiceImpl.getMyChallengeHistory(0L).size());
		assertEquals(1, (int) playerServiceImpl.getStatistic(0L).getLostGames());
		assertEquals(3, (int) playerServiceImpl.getStatistic(0L).getWonGames());

	}

	@Test
	public void shouldAddNewHoursAndEraseToAvailabilityPlayerTest() {
		// Given
		AvailabilityTimeTO availabilityTime = new AvailabilityTimeTO();
		availabilityTime.setStartTime(Instant.now().plusSeconds(3600));
		availabilityTime.setEndTime(Instant.now().plusSeconds(7200));

		// When

		// Then
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());
		playerServiceImpl.addMyAvailabilityTime(0L, availabilityTime);
		assertEquals(3, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());
		playerServiceImpl.eraseMyAvailabilityTime(0L, availabilityTime);
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getPlayerAvailabilityList().size());

	}

	@Test
	public void shouldChangeStatusToOfflineAndSetMessageTest() {
		// Given
		playerRepositoryImpl.getPlayerAvailabilityList(3L).clear();
		playerServiceImpl.addMyAvailabilityTime(3L,
				new AvailabilityTimeTO(Instant.ofEpochSecond(1230000000), Instant.ofEpochSecond(1230003600)));

		// When

		// Then
		assertEquals(1, playerRepositoryImpl.getPlayerAvailabilityList(3L).size());
		assertEquals(Status.ONLINE, playerRepositoryImpl.getPlayerAvailabilityList(3L).get(0).getStatus());
		assertEquals("", playerRepositoryImpl.getPlayerAvailabilityList(3L).get(0).getMessage());
		playerServiceImpl.changeStatusToOfflineOnMyAvailabilityTimeAndLeaveMessage(3L,
				new AvailabilityTimeTO(Instant.ofEpochSecond(1230000000), Instant.ofEpochSecond(1230003600)),
				"NewMessage");
		assertEquals(Status.OFFLINE, playerRepositoryImpl.getPlayerAvailabilityList(3L).get(0).getStatus());
		assertEquals("NewMessage", playerRepositoryImpl.getPlayerAvailabilityList(3L).get(0).getMessage());

	}

	@Test
	public void shouldNotThrowExceptionPlayerAvailabilityListIsEmptyTest() {
			// Then
		assertEquals(1, playerRepositoryImpl.getPlayer(1L).getPlayerAvailabilityList().size());

	}
}
