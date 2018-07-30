package com.example.game.service;

import java.util.List;
import java.util.Set;

import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.Statistic;
import com.example.game.transferObjects.AvailabilityTimeTO;
import com.example.game.transferObjects.ChallengeTO;
import com.example.game.transferObjects.GameTypeTO;
import com.example.game.transferObjects.PlayerProfile;

public interface PlayerService {

	Statistic getStatistic(Long playerId);

	Integer getMyLevel(Long playerId);

	Set<GameTypeTO> getMyGames(Long playerId);

	void eraseGameFromMyGames(Long playerId, String gameTitle);

	PlayerProfile getMyProfile(Long playerId);

	// Player getMyProfile(Long playerId);
	PlayerProfile editMyProfile(Long playerId, PlayerProfile playerProfil);

	void eraseMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO);

	List<PlayerProfile> getPlayerProfilesByFilter(PlayerProfile profilFilter);

	// Player login(String email, String password);
	List<ChallengeTO> getMyChallengeHistory(Long playerId);

	void addNewGameToMyGames(Long playerId, String gameTitle, Integer numberOfPlayers);


	List<PlayerProfile> getPlayerProfileList();

	void changeStatusToOfflineOnMyAvailabilityTimeAndLeaveMessage(Long playerId, AvailabilityTimeTO availabilityTimeTO,
																  String Message);

	Set<Game> getAllGamesInCollection();

	void changeMyPassword(Long playerId, PlayerProfile playerProfile);

	void addMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO);

}
