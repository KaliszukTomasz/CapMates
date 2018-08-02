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

    private static final int MINIMUM_TIME_TO_PLAY_GAME = 3599;
    @Autowired
    PlayerRepository playerRepository;


    /**
     * Method to show list of person who can play with us, based on availabilityTime
     *
     * @param playerId
     * @return
     */
    @Override
    public List<Meeting> getListByPlayerOfPossibleMeetings(Long playerId) {
        List<Meeting> listOfPossibleMeetings = new ArrayList<>();
        for (PlayerAvailability availability : playerRepository.getPlayerAvailabilityList(playerId)) {
            if (availability.getStatus() == Status.ONLINE) {
                Instant startTime = availability.getStartTime();
                Instant endTime = availability.getEndTime();
                for (Player player : playerRepository.getPlayers()) {
                    if (!player.getId().equals(playerId)) {
                        for (PlayerAvailability availabilityOpponent : playerRepository
                                .getPlayerAvailabilityList(player.getId())) {
                            Instant oppStartTime = availabilityOpponent.getStartTime();
                            Instant oppEndTime = availabilityOpponent.getEndTime();
                            if (startTime.isBefore(oppStartTime) && endTime.isAfter(oppStartTime)) {
                                if (endTime.isAfter(oppEndTime)) {
                                    compareToMinimumTimeGame(oppEndTime, oppStartTime, playerId, player.getId(), listOfPossibleMeetings);
                                } else if (endTime.isBefore(oppEndTime)) {
                                    compareToMinimumTimeGame(endTime, oppStartTime, playerId, player.getId(), listOfPossibleMeetings);
                                }
                            } else if (startTime.isAfter(oppStartTime) && startTime.isBefore(oppEndTime)) {
                                if (endTime.isBefore(oppEndTime)) {
                                    compareToMinimumTimeGame(endTime, startTime, playerId, player.getId(), listOfPossibleMeetings);
                                } else if (endTime.isAfter(oppEndTime)) {
                                    compareToMinimumTimeGame(oppEndTime, startTime, playerId, player.getId(), listOfPossibleMeetings);
                                }
                            }
                        }
                    }
                }
            }
        }

        return listOfPossibleMeetings;
    }

    /**
     * Method to compare if the difference in time is sufficient to play a game.
     * Minimum time is defined in MINIMUM_TIME_TO_PLAY_GAME
     *
     * @param endTime
     * @param startTime
     * @param playerMeId
     * @param playerOppId
     * @param list
     */
    private void compareToMinimumTimeGame(Instant endTime, Instant startTime,
                                          Long playerMeId, Long playerOppId, List<Meeting> list) {
        if (endTime.getEpochSecond() - startTime.getEpochSecond() >= MINIMUM_TIME_TO_PLAY_GAME) {
            Meeting meeting = new Meeting(startTime, endTime, playerOppId, playerMeId);
            list.add(meeting);
        }
    }

}