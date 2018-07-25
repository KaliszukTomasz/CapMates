package com.example.game.repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.core.api.AssertDelegateTarget;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.PlayerBuilder;
import com.example.game.entity.PlayerLevel;
import com.example.game.entity.Statistic;
import com.example.game.exceptions.SuchPlayerAlreadyExistsException;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerRepositoryTest {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	// @Test
	// public void shoudAddNewGameToPlayerGameCollectionTest(){//TODO problem,
	// gdy lista gier gracza jest pusta
	// playerRepositoryImpl.getPlayer(0L).getGames().add(new Game("Splendor",
	// 4));
	// assertEquals(1, playerRepositoryImpl.getPlayer(0L).getGames().size());
	// }

	@Test
	public void shouldGetActualPlayerStatisticsTest() {//TODO rzuca nullpointer
		Statistic stat = playerRepositoryImpl.getPlayerStaistics(0L);
		assertEquals(3, (int)stat.getWonGames());
		assertTrue(1 == stat.getLostGames());
	}

	@Test
	public void shouldEditPlayer() {
		Player newPlayer = new PlayerBuilder().setFirstName("Tome").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(3L).build();
		assertEquals("Romek", playerRepositoryImpl.getPlayer(3L).getFirstName());
		playerRepositoryImpl.editPlayer(newPlayer);
		assertEquals("Tome", playerRepositoryImpl.getPlayer(3L).getFirstName());

	}

	@Test(expected = SuchPlayerAlreadyExistsException.class)
	public void shouldThrowEmailAlreadyExistWhenAddNewPlayerToPlayerRepositoryTest() {

		Player player = new PlayerBuilder().setFirstName("Tome").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(2L).build();
		playerRepositoryImpl.addPlayer(player);
		

	}

	@Test
	public void shouldAddNewPlayerToPlayerRepositoryTest() {

		Player player = new PlayerBuilder().setFirstName("Tome").setEmail("tomek100@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(6L).build();
		assertEquals(5, playerRepositoryImpl.getPlayers().size());
		playerRepositoryImpl.addPlayer(player);
		assertEquals(6, playerRepositoryImpl.getPlayers().size());

	}

	
	
	@Test
	public void shouldAddAndEraseNewGameToPlayerGameCollectionTest() {
		Game game = new Game("Splendor3", 3);
		playerRepositoryImpl.getPlayer(0L).getGames().add(new Game("Splendor2", 4));
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getGames().size());
		playerRepositoryImpl.addGameToPlayerGames(0L, game);
		assertEquals(3, playerRepositoryImpl.getPlayerGames(0L).size());
		playerRepositoryImpl.eraseGameFromPlayerGames(0L, "Splendor3");
		assertEquals(2, playerRepositoryImpl.getPlayerGames(0L).size());
	}

	@Test
	public void somePlayerAlreadyExistTest() {
		assertNotNull(playerRepositoryImpl.getPlayer(0L));
	}

	@Test
	public void shouldShowNullWhenPlayerGameCollectionIsEmptyTest() {
		assertNull(playerRepositoryImpl.getPlayer(1L).getGames());
	}

}
