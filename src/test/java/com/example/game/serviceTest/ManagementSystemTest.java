package com.example.game.serviceTest;

import static org.junit.Assert.*;

import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.enums.Status;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Ranking;
import com.example.game.repository.ChallengeRepositoryImpl;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.ChallengeSeviceImpl;
import com.example.game.service.ManagementSystem;
import com.example.game.service.PlayerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementSystemTest {

	@Autowired
	ManagementSystem managementSystem;

	@Autowired
	PlayerServiceImpl playerServiceImpl;
	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	@Test
	public void should() {

	}

	@Test
	public void shouldTestAvailabilityHours() {
		Instant instant1 = Instant.parse("2018-07-03T16:00:00.00Z");
		Instant instant2 = Instant.parse("2018-07-03T18:16:00.00Z");
		Instant instant3 = Instant.parse("2018-07-03T17:16:00.00Z");
		Instant instant4 = Instant.parse("2018-07-03T19:16:00.00Z");
		Instant instant5 = Instant.parse("2018-07-03T17:00:00.00Z");
		Instant instant6 = Instant.parse("2018-07-03T20:16:00.00Z");
		Instant instant7 = Instant.parse("2018-07-04T17:00:00.00Z");
		Instant instant8 = Instant.parse("2018-07-04T20:16:00.00Z");
		Instant instant9 = Instant.parse("2018-07-04T17:16:00.00Z");
		Instant instant10 = Instant.parse("2018-07-04T18:16:00.00Z");
		Instant instant11 = Instant.parse("2018-07-04T19:00:00.00Z");
		Instant instant12 = Instant.parse("2018-07-04T20:17:00.00Z");
		PlayerAvailability playerAvailability1 = new PlayerAvailability(instant1, instant2);
		PlayerAvailability playerAvailability2 = new PlayerAvailability(instant3, instant4);
		PlayerAvailability playerAvailability3 = new PlayerAvailability(instant5, instant6);
		PlayerAvailability playerAvailability4 = new PlayerAvailability(instant7, instant8);
		PlayerAvailability playerAvailability5 = new PlayerAvailability(instant9, instant10);
		PlayerAvailability playerAvailability6 = new PlayerAvailability(instant11, instant12);
//		playerRepositoryImpl.getPlayerAvailabilityList(0L).clear();
//		playerRepositoryImpl.getPlayerAvailabilityList(1L).clear();
//		playerRepositoryImpl.getPlayerAvailabilityList(2L).clear();
//		playerRepositoryImpl.getPlayerAvailabilityList(3L).clear();
//		playerRepositoryImpl.getPlayerAvailabilityList(4L).clear();

		playerServiceImpl.addMyAvailabilityTime(0L, playerAvailability1);
		playerServiceImpl.addMyAvailabilityTime(1L, playerAvailability2);
		playerServiceImpl.addMyAvailabilityTime(2L, playerAvailability3);
		playerServiceImpl.addMyAvailabilityTime(0L, playerAvailability4);
		playerServiceImpl.addMyAvailabilityTime(3L, playerAvailability5);
		playerServiceImpl.addMyAvailabilityTime(3L, playerAvailability6);
		assertEquals(4, managementSystem.getListByPlayerOfPossibleMeetings(0L).size());
		assertEquals(2, managementSystem.getListByPlayerOfPossibleMeetings(1L).size());
		assertEquals(2, managementSystem.getListByPlayerOfPossibleMeetings(2L).size());
		assertEquals(2, managementSystem.getListByPlayerOfPossibleMeetings(2L).size());

	}

}
