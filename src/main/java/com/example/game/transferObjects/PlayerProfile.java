package com.example.game.transferObjects;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.example.game.entity.Game;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.PlayerLevel;
import com.example.game.entity.Ranking;
import com.example.game.entity.Statistic;


public class PlayerProfile {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String motto;
	private Statistic statistic;
	private Ranking ranking;
	private PlayerLevel level;
	private PlayerAvailability playerAvailability;
	private Set<Game> games = new HashSet<>();
	// private List<GameHistory> gameHistories = new ArrayList<>();

	public PlayerProfile() {
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

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public PlayerLevel getLevel() {
		return level;
	}

	public void setLevel(PlayerLevel level) {
		this.level = level;
	}

	public PlayerAvailability getPlayerAvailability() {
		return playerAvailability;
	}

	public void setPlayerAvailability(PlayerAvailability playerAvailability) {
		this.playerAvailability = playerAvailability;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

}
