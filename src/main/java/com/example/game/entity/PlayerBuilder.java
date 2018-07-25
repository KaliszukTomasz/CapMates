package com.example.game.entity;

import java.util.Set;

public class PlayerBuilder {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String motto;
	private Statistic statistic;
	private PlayerLevel level;
	private PlayerAvailability playerAvailability;
	private Set<Game> games;

	public PlayerBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public PlayerBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public PlayerBuilder setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public PlayerBuilder setEmail(String email) {
		this.email = email;
		return this;
	}

	public PlayerBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public PlayerBuilder setMotto(String motto) {
		this.motto = motto;
		return this;
	}

	public PlayerBuilder setStatistic(Statistic statistic) {
		this.statistic = statistic;
		return this;
	}

	public PlayerBuilder setLevel(PlayerLevel level) {
		this.level = level;
		return this;
	}

	public PlayerBuilder setPlayerAvailability(PlayerAvailability playerAvailability) {
		this.playerAvailability = playerAvailability;
		return this;
	}

	public PlayerBuilder setGames(Set<Game> games) {
		this.games = games;
		return this;
	}

	public Player build() {
		return new Player(id, firstName, lastName, email, password, motto, statistic, level, playerAvailability, games);
	}
}