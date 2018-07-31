package com.example.game.repositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
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

	@Test
	public void shouldGetActualPlayerStatisticsTest() {
		// given
		// when
		Statistic stat = playerRepositoryImpl.getPlayerStaistics(0L);
		// then
		assertEquals(3, (int) stat.getWonGames());
		assertTrue(1 == stat.getLostGames());
	}

	@Test
	public void shouldEditPlayer() {
		// given
		// when
		Player newPlayer = new PlayerBuilder().setFirstName("Tome").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password").setPlayerAvailabilityList(null)
				.setId(3L).build();
		// then
		assertEquals("Tomek", playerRepositoryImpl.getPlayer(3L).getFirstName());
		playerRepositoryImpl.editPlayer(newPlayer);
		assertEquals("Tome", playerRepositoryImpl.getPlayer(3L).getFirstName());

	}

	@Test(expected = SuchPlayerAlreadyExistsException.class)
	public void shouldThrowEmailAlreadyExistWhenAddNewPlayerToPlayerRepositoryTest() {
		// given
		// when
		Player player = new PlayerBuilder().setFirstName("Tome").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password").setPlayerAvailabilityList(null)
				.setId(2L).build();
		// then
		playerRepositoryImpl.addPlayer(player);

	}

	@Test
	public void shouldAddNewPlayerToPlayerRepositoryTest() {

		// given
		// when
		Player player = new PlayerBuilder().setFirstName("Tome").setEmail("tomek100@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password").setPlayerAvailabilityList(null)
				.setId(6L).build();
		// then
		assertEquals(5, playerRepositoryImpl.getPlayers().size());
		playerRepositoryImpl.addPlayer(player);
		assertEquals(6, playerRepositoryImpl.getPlayers().size());

	}

	@Test
	public void shouldAddAndEraseNewGameToPlayerGameCollectionTest() {
		// given
		Game game = new Game("Splendor3", 3);
		// when
		playerRepositoryImpl.getPlayer(0L).getGames().add(new Game("Splendor2", 4));
		// then
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getGames().size());
		// when
		playerRepositoryImpl.addGameToPlayerGames(0L, game);
		// then
		assertEquals(3, playerRepositoryImpl.getPlayerGames(0L).size());
		// when
		playerRepositoryImpl.eraseGameFromPlayerGames(0L, "Splendor3");
		// then
		assertEquals(2, playerRepositoryImpl.getPlayerGames(0L).size());
	}

	@Test
	public void somePlayerAlreadyExistTest() {
		// given
		// when
		// then
		assertNotNull(playerRepositoryImpl.getPlayer(0L));
	}

	@Test
	public void shouldShowNullWhenPlayerGameCollectionIsEmptyTest() {
		// given
		// when
		// then
		assertNull(playerRepositoryImpl.getPlayer(1L).getGames());
	}

}
