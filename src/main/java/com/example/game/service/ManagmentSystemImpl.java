package com.example.game.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enums.Status;
import com.example.game.entity.Meeting;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.repository.PlayerRepository;

@Service
public class ManagmentSystemImpl implements ManagementSystem {

	@Autowired
	PlayerRepository playerRepository;

	@Override
	public List<Meeting> getListByPlayerOfPossibleMeetings(Long playerId) {
		List<Meeting> listOfPossibleMeetings = new ArrayList<>();
		for (PlayerAvailability availability : playerRepository.getPlayerAvailabilityList(playerId)) {
			if (availability.getStatus() == Status.ONLINE) {
				Instant startTime = availability.getStartTime();
				Instant endTime = availability.getEndTime();
				for (Player player : playerRepository.getPlayers()) {
					if (player.getId() != playerId) {
						for (PlayerAvailability availabilityOpponent : playerRepository
								.getPlayerAvailabilityList(player.getId())) {
							Instant oppStartTime = availabilityOpponent.getStartTime();
							Instant oppEndTime = availabilityOpponent.getEndTime();
							if (startTime.isBefore(oppStartTime) && endTime.isAfter(oppStartTime)) {
								if (endTime.isAfter(oppEndTime)) {
									if (oppEndTime.getEpochSecond() - oppStartTime.getEpochSecond() >= 3599) {
										Meeting meeting = new Meeting(oppStartTime, oppEndTime, player.getId(),
												playerId);
										listOfPossibleMeetings.add(meeting);
									}
								} else if (endTime.isBefore(oppEndTime)) {
									if (endTime.getEpochSecond() - oppStartTime.getEpochSecond() > 3599) {
										Meeting meeting = new Meeting(oppStartTime, endTime, player.getId(), playerId);
										listOfPossibleMeetings.add(meeting);
									}
								}

							} else if (startTime.isAfter(oppStartTime) && startTime.isBefore(oppEndTime)) {
								if (endTime.isBefore(oppEndTime)) {
									if (endTime.getEpochSecond() - startTime.getEpochSecond() > 3599) {
										Meeting meeting = new Meeting(startTime, endTime, player.getId(), playerId);
										listOfPossibleMeetings.add(meeting);
									}
								} else if (endTime.isAfter(oppEndTime)) {
									if (oppEndTime.getEpochSecond() - startTime.getEpochSecond() > 3599) {
										Meeting meeting = new Meeting(startTime, oppEndTime, player.getId(), playerId);
										listOfPossibleMeetings.add(meeting);
									}
								}

							}
						}
					}
				}

			}
		}

		return listOfPossibleMeetings;
	}
}