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
import com.example.game.transferObjects.AvailabilityTimeTO;

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
		AvailabilityTimeTO playerAvailability1 = new AvailabilityTimeTO(instant1, instant2);
		AvailabilityTimeTO playerAvailability2 = new AvailabilityTimeTO(instant3, instant4);
		AvailabilityTimeTO playerAvailability3 = new AvailabilityTimeTO(instant5, instant6);
		AvailabilityTimeTO playerAvailability4 = new AvailabilityTimeTO(instant7, instant8);
		AvailabilityTimeTO playerAvailability5 = new AvailabilityTimeTO(instant9, instant10);
		AvailabilityTimeTO playerAvailability6 = new AvailabilityTimeTO(instant11, instant12);
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
