package com.example.game.repositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTypeRepositoryTest {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;

	@Test
	public void somePlayerAlreadyExist() {
		// given
		// when
		// then
		assertNotNull(playerRepositoryImpl.getPlayer(0L));
	}

	@Test
	public void shouldAddNewGameToPlayerGameCollection() {
		// given
		assertEquals(1, playerRepositoryImpl.getPlayer(0L).getGames().size());
		// when
		playerRepositoryImpl.addGameToPlayerGames(0L, new Game("newGame", 3));
		// then
		assertEquals(2, playerRepositoryImpl.getPlayer(0L).getGames().size());
		
		playerRepositoryImpl.eraseGameFromPlayerGames(0L, "newGame");
		assertEquals(1, playerRepositoryImpl.getPlayer(0L).getGames().size());
	}

	@Test
	public void shouldNotAddTheGameWhatExistInBase() {
		//given
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
		//when
		gameTypeRepository.addGameToGamesContainer(new Game("Splendor", 4));
		//then
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
	}

	@Test
	public void shouldAddTheNewGameToGameCollection() {
		//given
		Game game = new Game("Splendor2", 4);
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
		//when
		gameTypeRepository.addGameToGamesContainer(game);
		//then
		assertEquals(4, gameTypeRepository.getGamesContainer().size());
		gameTypeRepository.eraseGameFromGamesContainer(game);
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
	}

}
