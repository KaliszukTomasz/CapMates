package com.example.game.repository;

import java.util.ArrayList;
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
		players.add(new PlayerBuilder().setFirstName("Tomek").setEmail("tomek1@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password").setGames(secik).
				setPlayerAvailability(new PlayerAvailability()).setId(0L).setStatistic(new Statistic()).build());
		players.add(new PlayerBuilder().setFirstName("Zosia").setEmail("tomek2@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(1L).build());
		players.add(new PlayerBuilder().setFirstName("Kasia").setEmail("tomek3@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(2L).build());
		players.add(new PlayerBuilder().setFirstName("Romek").setEmail("tomek4@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(3L).build());
		players.add(new PlayerBuilder().setFirstName("Zuzia").setEmail("tomek5@wp.pl").setLastName("Pierwszy")
				.setLevel(new PlayerLevel()).setMotto("motto1").setPassword("password")
				.setPlayerAvailability(new PlayerAvailability()).setId(4L).build());
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
	public void addHoursAvailability(String timeFrom, String timeTo, Long playerId) {
		Player player = getPlayer(playerId);
		PlayerAvailability playerAvailability = new PlayerAvailability();
		playerAvailability.setStartTime(timeFrom);
		playerAvailability.setEndTime(timeTo);
		player.setPlayerAvailability(playerAvailability);
	}

	@Override//TODO
	public void editHoursAvailability(String timeFrom, String timeTo, Long playerId) {
		Player player = getPlayer(playerId);
		PlayerAvailability playerAvailability = player.getPlayerAvailability();
		playerAvailability.setEndTime(timeTo);
		playerAvailability.setStartTime(timeFrom);
		player.setPlayerAvailability(playerAvailability);
	}

	@Override
	public String getHoursAvailability(Long playerId) {
		String startTime = getPlayer(playerId).getPlayerAvailability().getStartTime();
		String endTime = getPlayer(playerId).getPlayerAvailability().getEndTime();
		return "Hours avaliability: " + startTime + " - " + endTime;
	}

	@Override
	public PlayerAvailability getPlayerAvailability(Long playerId) {
		Player player = getPlayer(playerId);
		return player.getPlayerAvailability();
	}

	@Override
	public Statistic getStatistics(Long playerId) {
		Player player = getPlayer(playerId);
		return player.getStatistic();
	}

	@Override
	public Integer getPlayerLevel(Long playerId) {
		Player player = getPlayer(playerId);
		PlayerLevel playerLevel = player.getLevel();
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
