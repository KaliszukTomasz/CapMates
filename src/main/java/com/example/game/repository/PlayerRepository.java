package com.example.game.repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;

public interface PlayerRepository {

	Long addPlayer(Player player);

	Long editPlayer(Player player);

	void addHoursAvailability(Instant timeFrom, Instant timeTo, Long playerId);

	void eraseHoursAvailability(Instant timeFrom, Instant timeTo, Long playerId);

	Statistic getStatistics(Long playerId);

	Integer getPlayerLevel(Long playerId);

	Set<Game> getPlayerGames(Long playerId);

	void eraseGameFromPlayerGames(Long playerId, String gameTitle);

	void addGameToPlayerGames(Long playerId, Game game);

	Player getPlayer(Long playerId);

	Statistic getPlayerStaistics(Long playerId);

	List<PlayerAvailability> getPlayerAvailabilityList(Long playerId);

	List<Player> getPlayers();

	void setPlayers(List<Player> players);

}
