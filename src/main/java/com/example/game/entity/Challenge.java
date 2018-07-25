package com.example.game.entity;

import java.time.LocalDateTime;

public class Challenge {


	private Long id;
	private LocalDateTime date;
	private String gameTitle;
	private Long playerId;
	private Integer score;
	



	
	public Challenge(Long id, LocalDateTime date, String gameTitle, Long playerId, Integer score) {
		super();
		this.id = id;
		this.date = date;
		this.gameTitle = gameTitle;
		this.playerId = playerId;
		this.score = score;
		
	}


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
