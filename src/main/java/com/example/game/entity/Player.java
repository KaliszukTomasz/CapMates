package com.example.game.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.example.game.exceptions.NoGamesAddedToThisAccountPlayerException;

public class Player {

	private static Long staticId; // atomic long
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String motto;
	private Statistic statistic;
	// private Ranking ranking;
	private PlayerLevel level;
	private PlayerAvailability playerAvailability;
	private Set<Game> games = Collections.emptySet();
	// private List<GameHistory> gameHistories = new ArrayList<>();

	public Player() { // TODO wszystkie setId -> this.id = id powinno wylecieć?
		id = staticId;
		staticId++;
	}

	public Player(Long id, String firstName, String lastName, String email, String password, String motto,
			Statistic statistic, PlayerLevel level, PlayerAvailability playerAvailability, Set<Game> games) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.motto = motto;
		this.statistic = statistic;
		// this.ranking = ranking;
		this.level = level;
		this.playerAvailability = playerAvailability;
		this.games = games;
		// this.gameHistories = gameHistories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	// public Ranking getRanking() {
	// return ranking;
	// }
	//
	// public void setRanking(Ranking ranking) {
	// this.ranking = ranking;
	// }

	public PlayerLevel getLevel() {
		return level;
	}

	public void setLevel(PlayerLevel level) {
		this.level = level;
	}

	// public List<GameHistory> getGameHistories() {
	// return gameHistories;
	// }
	//
	// public void setGameHistories(List<GameHistory> gameHistories) {
	// this.gameHistories = gameHistories;
	// }

	public static Long getStaticId() {
		return staticId;
	}

	public static void setStaticId(Long staticId) {
		Player.staticId = staticId;
	}

	public PlayerAvailability getPlayerAvailability() {
		return playerAvailability;
	}

	public void setPlayerAvailability(PlayerAvailability playerAvailability) {
		this.playerAvailability = playerAvailability;
	}

	public Set<Game> getGames() {
//		if (this.games != null) {
			return games;
//		} else {
//			throw new NoGamesAddedToThisAccountPlayerException("GameSet this Player is empty!");
//		}
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}
}
