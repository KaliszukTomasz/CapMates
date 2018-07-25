package com.example.game.entity;

import java.time.Instant;

public class Meeting {

	Instant startTime;
	Instant endTime;
	Long opponentId;
	Long myId;

	public Meeting() {
	}

	public Meeting(Instant startTime, Instant endTime, Long opponentId, Long myId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.opponentId = opponentId;
		this.myId = myId;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public Long getOpponentId() {
		return opponentId;
	}

	public void setOpponentId(Long opponentId) {
		this.opponentId = opponentId;
	}

	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}

}