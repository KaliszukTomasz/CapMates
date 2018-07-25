package com.example.game.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game.entity.Challenge;
import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;
import com.example.game.exceptions.UnauthorizedLoginException;
import com.example.game.mappers.ProfilPlayerMapper;
import com.example.game.repository.ChallengeRepository;
import com.example.game.repository.PlayerRepository;
import com.example.game.transferObjects.PlayerProfile;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private ChallengeRepository challengeRepository;

	@Autowired
	private ProfilPlayerMapper profilPlayerMapper;
	
	@Override
	public Statistic getStatistic(Long playerId) {
		return playerRepository.getPlayerStaistics(playerId);
	}

	@Override
	public Integer getMyLevel(Long playerId) {
		return playerRepository.getPlayerLevel(playerId);
	}

	
	@Override
	public List<Challenge> getMyChallengeHistory(Long playerId) {
		return challengeRepository.getPlayerAllChallengeHistory(playerId);
	}

	@Override
	public Set<Game> getMyGames(Long playerId) {
		return playerRepository.getPlayerGames(playerId);
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
		player.setPlayerAvailability(playerProfil.getPlayerAvailability());
		
		playerRepository.editPlayer(player);
		return getMyProfile(playerId);
	}

	@Override
	public void addMyAvailabilityTime(Long playerId, PlayerAvailability availabilityTime) {
		playerRepository.addHoursAvailability(availabilityTime.getStartTime(), availabilityTime.getEndTime(), playerId);

	}

	@Override
	public void editMyAvailabilityTime(Long playerId, PlayerAvailability availabilityTime) {
		playerRepository.editHoursAvailability(availabilityTime.getStartTime(), availabilityTime.getEndTime(),
				playerId);

	}
//	@Override
//	public Player login(String email, String password) {
//		Optional<Player> actualPlayer = playerRepository.getPlayers().stream().filter(gamer -> gamer.getEmail().equals(email))
//				.findFirst();
//		if (!actualPlayer.isPresent()) {
//			throw new UnauthorizedLoginException("Email doeasnt exist!");
//		}
//		Player player = actualPlayer.get();
//		if (!player.getPassword().equals(password)) {
//			throw new UnauthorizedLoginException("Password is incorrect!");
//		}
//
//		return player;
//	}
}
