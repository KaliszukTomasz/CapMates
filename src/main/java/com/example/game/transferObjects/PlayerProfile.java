package com.example.game.transferObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.game.entity.Game;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.PlayerLevel;
import com.example.game.entity.Statistic;

public class PlayerProfile {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String motto;
	private Statistic statistic;
	private PlayerLevel level;
	private String password;
	private List<PlayerAvailability> playerAvailabilityList = new ArrayList<>();
	private Set<Game> games = new HashSet<>();



	public PlayerProfile() {
	}

	public PlayerProfile(Long id, String firstName, String lastName, String email, String motto, Statistic statistic, PlayerLevel level, String password, List<PlayerAvailability> playerAvailabilityList, Set<Game> games) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.motto = motto;
		this.statistic = statistic;
		this.level = level;
		this.password = password;
		this.playerAvailabilityList = playerAvailabilityList;
		this.games = games;
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

	public PlayerLevel getLevel() {
		return level;
	}

	public void setLevel(PlayerLevel level) {
		this.level = level;
	}

	public List<PlayerAvailability> getPlayerAvailabilityList() {
		return playerAvailabilityList;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
