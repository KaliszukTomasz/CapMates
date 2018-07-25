package com.example.game.repository;

import java.util.List;
import java.util.Set;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;

public interface PlayerRepository {

	Long addPlayer(Player player);

	Long editPlayer(Player player);

	void addHoursAvailability(String timeFrom, String timeTo, Long playerId);

	void editHoursAvailability(String timeFrom, String timeTo, Long playerId);

	String getHoursAvailability(Long playerId);

	Statistic getStatistics(Long playerId);

	Integer getPlayerLevel(Long playerId);

	Set<Game> getPlayerGames(Long playerId);

	void eraseGameFromPlayerGames(Long playerId, String gameTitle);

	void addGameToPlayerGames(Long playerId, Game game);

	Player getPlayer(Long playerId);

	Statistic getPlayerStaistics(Long playerId);

	PlayerAvailability getPlayerAvailability(Long playerId);

	List<Player> getPlayers();

	void setPlayers(List<Player> players);

}
