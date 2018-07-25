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
		assertNotNull(playerRepositoryImpl.getPlayer(0L));
	}

	@Test
	public void shouldAddNewGameToPlayerGameCollection() {
		assertNotNull(playerRepositoryImpl.getPlayer(0L).getGames());
	}

	@Test
	public void shouldNotAddTheGameWhatExistInBase() {
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
		gameTypeRepository.addGameToGamesContainer(new Game("Splendor", 4));
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
	}

	@Test
	public void shouldAddTheNewGameToGameCollection() {
		Game game = new Game("Splendor2", 4);
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
		gameTypeRepository.addGameToGamesContainer(game);
		assertEquals(4, gameTypeRepository.getGamesContainer().size());
		gameTypeRepository.eraseGameFromGamesContainer(game);
		assertEquals(3, gameTypeRepository.getGamesContainer().size());
	}

}
