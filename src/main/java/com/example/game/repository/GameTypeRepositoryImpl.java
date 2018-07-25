package com.example.game.repository;

import java.util.HashSet;
import java.util.Set;
//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.example.game.entity.Game;
import com.example.game.exceptions.InvalidNameOfTheSearchedGameException;

@Repository
public class GameTypeRepositoryImpl implements GameTypeRepository {

	private Set<Game> gamesContainer = new HashSet<>();

	// private static AtomicLong staticId = new AtomicLong(0);

	public GameTypeRepositoryImpl() {
		addGameToGamesContainer(new Game("Chess", 2));
		addGameToGamesContainer(new Game("Monopoly", 4));
		addGameToGamesContainer(new Game("Splendor", 4));
	}

	@Override
	public Set<Game> getGamesContainer() {
		return gamesContainer;
	}

	@Override
	public void setGamesContainer(Set<Game> gamesContainer) {
		this.gamesContainer = gamesContainer;
	}

	@Override
	public void addGameToGamesContainer(Game game) {
		if (this.gamesContainer.stream().anyMatch(oldGame -> oldGame.getTitle().equals(game.getTitle()))) {
			return;
		} else {
			this.gamesContainer.add(game);
		}
	}

	@Override
	public void eraseGameFromGamesContainer(Game game) {
		if (this.gamesContainer.stream().anyMatch(oldGame -> oldGame.getTitle().equals(game.getTitle()))) {
			this.gamesContainer.removeIf(actualGame -> actualGame.getTitle().equals(game.getTitle()));
		}

	}

	@Override
	public Integer getNumerOfPlayersThisGame(String nameOfTheGame) {
		for (Game game : gamesContainer) {
			if (game.getTitle().equals(nameOfTheGame)) {
				return game.getNumberOfPlayers();
			}
		}
		throw new InvalidNameOfTheSearchedGameException("No such name of the game in the container");
	}

}
