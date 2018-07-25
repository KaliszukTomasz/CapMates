package com.example.game.repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.game.entity.Challenge;
import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.PlayerBuilder;
import com.example.game.entity.PlayerLevel;
import com.example.game.entity.Statistic;
import com.example.game.exceptions.SuchPlayerAlreadyExistsException;
import com.example.game.exceptions.UserNotFondException;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

	private List<Player> players = new ArrayList<>();

	Integer AMOUNT_POINTS_PER_WIN = 10;
	Integer AMOUNT_POINTS_PER_LOSE = 1;

	@Autowired
	ChallengeRepository challengeRepository;

	public PlayerRepositoryImpl() {
		Set<Game> secik = new HashSet<>();
		secik.add(new Game("Splendor", 4));
		List<PlayerAvailability> availabilityList = new ArrayList<>();
		availabilityList.add(new PlayerAvailability(Instant.now(), Instant.now()));
		players.add(new PlayerBuilder().setFirstName("Tomek").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password").setGames(secik).
				setPlayerAvailabilityList(null).setId(0L).setStatistic(new Statistic()).build());
		players.add(new PlayerBuilder().setFirstName("Zosia").setEmail("tomek2@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailabilityList(null).setId(1L).build());
		players.add(new PlayerBuilder().setFirstName("Kasia").setEmail("tomek3@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailabilityList(null).setId(2L).build());
		players.add(new PlayerBuilder().setFirstName("Romek").setEmail("tomek4@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailabilityList(null).setId(3L).build());
		players.add(new PlayerBuilder().setFirstName("Zuzia").setEmail("tomek5@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailabilityList(null).setId(4L).build());
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public Long addPlayer(Player player) {

		for (Player actualPlayer : players) {
			if (actualPlayer.getEmail().equals(player.getEmail())) {
				throw new SuchPlayerAlreadyExistsException("Email or Id already exist");
			}
		}
		players.add(player);
		return player.getId();
	}

	@Override
	public Long editPlayer(Player player) {
		Player actualPlayer = getPlayer(player.getId());
		actualPlayer.setFirstName(player.getFirstName());
		actualPlayer.setLastName(player.getLastName());
		actualPlayer.setEmail(player.getEmail());
		actualPlayer.setPassword(player.getPassword());
		actualPlayer.setMotto(player.getMotto());
		return actualPlayer.getId();
	}

	@Override//TODO
	public void addHoursAvailability(PlayerAvailability playerAvailability, Long playerId) {
		Player player = getPlayer(playerId);
		PlayerAvailability playerAvail = new PlayerAvailability();
		playerAvail.setStartTime(playerAvailability.getStartTime());
		playerAvail.setEndTime(playerAvailability.getEndTime());
		playerAvail.setStatus(playerAvailability.getStatus());
		playerAvail.setMessage(playerAvailability.getMessage());
		player.getPlayerAvailabilityList().add(playerAvail);
	}

	@Override//TODO
	public void eraseHoursAvailability(Instant timeFrom, Instant timeTo, Long playerId) {
		Player player = getPlayer(playerId);
		PlayerAvailability playerAvailability = new PlayerAvailability();
		playerAvailability.setEndTime(timeTo);
		playerAvailability.setStartTime(timeFrom);
		player.getPlayerAvailabilityList().removeIf(time -> time.getStartTime().equals(timeFrom)&&time.getEndTime().equals(timeTo));
//		for(PlayerAvailability availability : player.getPlayerAvailabilityList()){
//			if(availability.getStartTime().equals(timeFrom) && availability.getEndTime().equals(timeTo)){
//				player.getPlayerAvailabilityList().remove(availability);
//			}
		}
	

	

	@Override
	public List<PlayerAvailability> getPlayerAvailabilityList(Long playerId) {
		Player player = getPlayer(playerId);
		return player.getPlayerAvailabilityList();
	}

	@Override
	public Statistic getStatistics(Long playerId) {
		Player player = getPlayer(playerId);
		return player.getStatistic();
	}

	@Override
	public Integer getActualPlayerLevel(Long playerId) {
		Player player = getPlayer(playerId);
		PlayerLevel playerLevel = new PlayerLevel();
		for(Challenge challenge : challengeRepository.getPlayerAllChallengeHistory(playerId)){
			playerLevel.setCurrentExp(playerLevel.getCurrentExp()+challenge.getScore()*10);
		}
		playerLevel.setLevel(playerLevel.getCurrentExp()/playerLevel.getExpToNextLvl() +1);
		player.setLevel(playerLevel);
		return playerLevel.getLevel();
	}

	@Override
	public Player getPlayer(Long playerId) {
		Optional<Player> optionalPlayer = players.stream().filter(player -> player.getId().equals(playerId))
				.findFirst();
		if (!optionalPlayer.isPresent()) {
			throw new UserNotFondException("User with id: " + playerId + ", not found");
		}
		return optionalPlayer.get();
	}

	@Override
	public Set<Game> getPlayerGames(Long playerId) {
		Player player = getPlayer(playerId);
		return player.getGames();
	}

	@Override
	public void eraseGameFromPlayerGames(Long playerId, String gameTitle) {
		Player player = getPlayer(playerId);
		
		Set<Game> gameSet = player.getGames();
		if (gameSet.stream().anyMatch(game -> game.getTitle().equals(gameTitle))) {
			gameSet.removeIf(game -> game.getTitle().equals(gameTitle));
		}
		
	}

	@Override
	public void addGameToPlayerGames(Long playerId, Game game) {
		Player player = getPlayer(playerId);
		Set<Game> gameSet = player.getGames();
		gameSet.add(game);
		
	}

	@Override
	public Statistic getPlayerStaistics(Long playerId) {
		Player player = getPlayer(playerId);
		player.setStatistic(new Statistic(0, 0));
		for (Challenge challenge : challengeRepository.getPlayerAllChallengeHistory(playerId)) {
			if (challenge.getScore() == AMOUNT_POINTS_PER_WIN) {
				player.getStatistic().setWonGames(player.getStatistic().getWonGames() + 1);
			} else {
				player.getStatistic().setLostGames(player.getStatistic().getLostGames() + 1);
			}

		}
		return player.getStatistic();

	}

	
}
