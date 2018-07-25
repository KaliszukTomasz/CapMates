package com.example.game.repository;

import java.util.Set;

import com.example.game.entity.Game;

public interface GameTypeRepository {

	Set<Game> getGamesContainer();

	void setGamesContainer(Set<Game> gamesContainer);

	void addGameToGamesContainer(Game game);

	Integer getNumerOfPlayersThisGame(String nameOfTheGame);

	void eraseGameFromGamesContainer(Game game);
	

}
