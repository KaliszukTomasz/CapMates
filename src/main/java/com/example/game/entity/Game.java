package com.example.game.entity;

public class Game {

	private String title;
	private Integer numberOfPlayers;

	public Game() {

	}

	public Game(String title, Integer numberOfPlayers) {
		super();

		this.title = title;
		this.numberOfPlayers = numberOfPlayers;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(Integer numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
}
