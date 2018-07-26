package com.example.game.entity;

import java.time.Instant;

import com.example.enums.Status;

public class PlayerAvailability {

	private Instant startTime;
	private Instant endTime;
	private Status status;
	private String message;

	public PlayerAvailability() {
		this.status = Status.ONLINE;
		this.message = "";
	}

	public PlayerAvailability(Instant startTime, Instant endTime, Status status, String message) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.message = message;
	}

	public PlayerAvailability(Instant startTime, Instant endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = Status.ONLINE;
		this.message = "";
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}
