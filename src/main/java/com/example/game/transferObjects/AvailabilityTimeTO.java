package com.example.game.transferObjects;

import java.time.Instant;

public class AvailabilityTimeTO {
	
	
	Instant startTime;
	Instant endTime;

	public AvailabilityTimeTO() {

	}
	
	

	public AvailabilityTimeTO(Instant startTime, Instant endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
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

