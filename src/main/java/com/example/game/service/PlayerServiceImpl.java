package com.example.game.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enums.Status;
import com.example.game.entity.Challenge;
import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;
import com.example.game.mappers.ChallengeHistoryMapper;
import com.example.game.mappers.GameTypeSetMapper;
import com.example.game.mappers.ProfilPlayerMapper;
import com.example.game.repository.ChallengeRepository;
import com.example.game.repository.GameTypeRepository;
import com.example.game.repository.PlayerRepository;
import com.example.game.transferObjects.AvailabilityTimeTO;
import com.example.game.transferObjects.ChallengeTO;
import com.example.game.transferObjects.GameTypeTO;
import com.example.game.transferObjects.PlayerProfile;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private ChallengeRepository challengeRepository;

	@Autowired
	private ProfilPlayerMapper profilPlayerMapper;

	@Autowired
	private GameTypeRepository gameTypeRepository;

	@Autowired
	private ChallengeHistoryMapper challengeHistoryMapper;

	@Autowired
	private GameTypeSetMapper gameTypeListMapper;

	@Override
	public Statistic getStatistic(Long playerId) {
		return playerRepository.getPlayerStaistics(playerId);
	}

	@Override
	public Integer getMyLevel(Long playerId) {
		return playerRepository.getActualPlayerLevel(playerId);
	}

	@Override
	public List<ChallengeTO> getMyChallengeHistory(Long playerId) {
		List<Challenge> challengeList = challengeRepository.getPlayerAllChallengeHistory(playerId);
		List<ChallengeTO> challengeToList = challengeHistoryMapper.mapChallengeHistory(challengeList);
		return challengeToList;
	}

	@Override
	public Set<GameTypeTO> getMyGames(Long playerId) {
		return gameTypeListMapper.mapToGameTypeTOSet(playerRepository.getPlayerGames(playerId));
	}

	@Override
	public Set<Game> getAllGamesInCollection() {
		return gameTypeRepository.getGamesContainer();
	}

	@Override
	public void eraseGameFromMyGames(Long playerId, String gameTitle) {
		playerRepository.eraseGameFromPlayerGames(playerId, gameTitle);
	}

	@Override
	public void addNewGameToMyGames(Long playerId, String gameTitle, Integer numberOfPlayers) {
		Game game = new Game();
		game.setNumberOfPlayers(numberOfPlayers);
		game.setTitle(gameTitle);
		playerRepository.addGameToPlayerGames(playerId, game);
		gameTypeRepository.addGameToGamesContainer(game);
	}

	@Override
	public PlayerProfile getMyProfile(Long playerId) {
		Player player = playerRepository.getPlayer(playerId);
		return profilPlayerMapper.mapToPlayerProfile(player);
	}

	@Override
	public PlayerProfile editMyProfile(Long playerId, PlayerProfile playerProfil) {
		Player player = playerRepository.getPlayer(playerId);
		player.setFirstName(playerProfil.getFirstName());
		player.setLastName(playerProfil.getLastName());
		player.setGames(playerProfil.getGames());
		player.setMotto(playerProfil.getMotto());

		playerRepository.editPlayer(player);
		return getMyProfile(playerId);
	}

	@Override
	public void changeMyPassword(Long playerId, PlayerProfile playerProfile) {
		Player player = playerRepository.getPlayer(playerId);
		player.setPassword(playerProfile.getPassword());
	}

	@Override
	public void addMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO) {

		PlayerAvailability availabilityTime = new PlayerAvailability();
		availabilityTime.setEndTime(availabilityTimeTO.getEndTime());
		availabilityTime.setStartTime(availabilityTimeTO.getStartTime());

		playerRepository.addHoursAvailability(availabilityTime, playerId);
	}

	@Override
	public void eraseMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO) {
		playerRepository.eraseHoursAvailability(availabilityTimeTO.getStartTime(), availabilityTimeTO.getEndTime(),
				playerId);

	}

	@Override
	public void changeStatusToOfflineOnMyAvailabilityTimeAndLeaveMessage(Long playerId,
			AvailabilityTimeTO availabilityTimeTO, String message) {
		Player player = playerRepository.getPlayer(playerId);

		for (PlayerAvailability availability : player.getPlayerAvailabilityList()) {
			if (availability.getStartTime().equals(availabilityTimeTO.getStartTime())
					&& availability.getEndTime().equals(availabilityTimeTO.getEndTime())) {
				availability.setStatus(Status.OFFLINE);
				availability.setMessage(message);
			}

		}
	}
	// @Override
	// public Player login(String email, String password) {
	// Optional<Player> actualPlayer =
	// playerRepository.getPlayers().stream().filter(gamer ->
	// gamer.getEmail().equals(email))
	// .findFirst();
	// if (!actualPlayer.isPresent()) {
	// throw new UnauthorizedLoginException("Email doeasnt exist!");
	// }
	// Player player = actualPlayer.get();
	// if (!player.getPassword().equals(password)) {
	// throw new UnauthorizedLoginException("Password is incorrect!");
	// }
	//
	// return player;
	// }

}
