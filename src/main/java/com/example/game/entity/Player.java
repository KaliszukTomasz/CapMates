package com.example.game.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
	private List<PlayerAvailability> playerAvailabilityList = Collections.emptyList();
	private Set<Game> games = Collections.emptySet();
	// private List<GameHistory> gameHistories = new ArrayList<>();

	public Player() {
		id = staticId;
		staticId++;
	}

	public Player(Long id, String firstName, String lastName, String email, String password, String motto,
			Statistic statistic, PlayerLevel level, List<PlayerAvailability> playerAvailabilityList, Set<Game> games) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.motto = motto;
		this.statistic = statistic;
		// this.ranking = ranking;
		this.level = level;
		this.playerAvailabilityList = playerAvailabilityList;
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

	public PlayerLevel getLevel() {
		return level;
	}

	public void setLevel(PlayerLevel level) {
		this.level = level;
	}

	public List<PlayerAvailability> getPlayerAvailabilityList() {
		if (this.playerAvailabilityList == null) {
			this.playerAvailabilityList = new ArrayList<>();
			return this.playerAvailabilityList;
		} else {
			return playerAvailabilityList;
		}
	}

	public void setPlayerAvailabilityList(List<PlayerAvailability> playerAvailabilityList) {
		this.playerAvailabilityList = playerAvailabilityList;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

}
