package com.example.game.entity;

public class Statistic {

	private Integer wonGames = 0;
	private Integer lostGames = 0;

	public Statistic() {
	}

	public Statistic(Integer wonGames, Integer lostGames) {
		super();
		this.wonGames = wonGames;
		this.lostGames = lostGames;
	}

	public Integer getWonGames() {
		return wonGames;
	}

	public void setWonGames(Integer wonGames) {
		this.wonGames = wonGames;
	}

	public Integer getLostGames() {
		return lostGames;
	}

	public void setLostGames(Integer lostGames) {
		this.lostGames = lostGames;
	}
}
