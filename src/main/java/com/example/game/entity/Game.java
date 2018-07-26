package com.example.game.entity;

public class Game {

	// private Long id;
	private String title;
	private Integer numberOfPlayers;

	public Game() {

	}

	public Game(String title, Integer numberOfPlayers) {
		super();
		// this.id = id;
		this.title = title;
		this.numberOfPlayers = numberOfPlayers;
	}

	// public Long getId() {
	// return id;
	// }
	//
	// public void setId(Long id) {
	// this.id = id;
	// }

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
