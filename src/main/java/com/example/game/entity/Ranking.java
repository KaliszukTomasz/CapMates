package com.example.game.entity;

public class Ranking {

//	private static Long staticId;
	private Long id;
	private Integer points;

//	public Ranking() {
//		this.id = staticId;
//		staticId++;
//	}

	public Ranking(Integer points) {
		super();
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
