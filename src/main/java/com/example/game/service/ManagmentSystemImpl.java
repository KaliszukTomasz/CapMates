package com.example.game.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.repository.PlayerRepository;

@Service
public class ManagmentSystemImpl implements ManagementSystem {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public List<Player> getListPlayersToConnect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGameToHistory(LocalDateTime date, Game game, Long winnerId, List<Long> lostId) {
		// TODO Auto-generated method stub
		
	}

		
//	@Override
//	public void addChallengeToHistory(LocalDateTime date, Game game, Long winnerId, List<Long> lostId) {
//		GameHistory gameHistory = new GameHistory();
//		gameHistory.setDate(date);
//		gameHistory.setGame(game);
//		gameHistory.setWinnerId(winnerId);
//		gameHistory.setLostId(lostId);
//		database.getGameHistories().add(gameHistory);
//
//	}private static Long staticId;
//	private Long id;
//	private LocalDateTime date;
//	private Long gameId;
//	private Long playerId;
//	private Integer score;
}
