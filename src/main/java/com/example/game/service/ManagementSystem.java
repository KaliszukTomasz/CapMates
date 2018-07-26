package com.example.game.service;

import java.util.List;

import com.example.game.entity.Meeting;

public interface ManagementSystem {

	List<Meeting> getListByPlayerOfPossibleMeetings(Long playerId);

	
}
