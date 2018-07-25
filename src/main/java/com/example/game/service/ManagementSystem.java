package com.example.game.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.game.entity.Game;
import com.example.game.entity.Player;

public interface ManagementSystem {

	List<Player> getListPlayersToConnect();
//	void updatePlayersRanking();
//	void updatePlayersStatistics();
	void addGameToHistory(LocalDateTime date, Game game, Long winnerId, List<Long> lostId);
}