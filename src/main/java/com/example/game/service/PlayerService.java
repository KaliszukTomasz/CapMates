package com.example.game.service;

import java.util.List;
import java.util.Set;

import com.example.game.entity.Challenge;
import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;
import com.example.game.transferObjects.PlayerProfile;

public interface PlayerService {

	Statistic getStatistic(Long playerId);
	Integer getMyLevel (Long playerId);
	Set<Game> getMyGames (Long playerId);
	void eraseGameFromMyGames (Long playerId, String gameTitle);
	PlayerProfile getMyProfile(Long playerId);
	
//	Player getMyProfile(Long playerId);
	PlayerProfile editMyProfile(Long playerId, PlayerProfile playerProfil);
	void addMyAvailabilityTime(Long playerId, PlayerAvailability availabilityTime);
	void editMyAvailabilityTime (Long playerId, PlayerAvailability availabilityTime);
	Player login(String email, String password);
	List<Challenge> getMyChallengeHistory(Long playerId);
	void addNewGameToMyGames(Long playerId, String gameTitle, Integer numberOfPlayers);
	
}
