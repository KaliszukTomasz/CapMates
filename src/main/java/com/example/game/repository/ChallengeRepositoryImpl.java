package com.example.game.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.game.entity.Challenge;

@Repository
public class ChallengeRepositoryImpl implements ChallengeRepository {

	List<Challenge> challengeList = new ArrayList<>();

	Integer AMOUNT_POINTS_PER_WIN = 10;
	Integer AMOUNT_POINTS_PER_LOSE = 1;

	
	@Override
	public String toString() {
		return "ChallengeRepositoryImpl [challengeList=" + challengeList + "]";
	}

	public ChallengeRepositoryImpl() {
		challengeList.add(new Challenge(0L, LocalDateTime.now(), "Chess", 0L, 10));
		challengeList.add(new Challenge(1L, LocalDateTime.now(), "Chess", 1L, 10));
		challengeList.add(new Challenge(2L, LocalDateTime.now(), "Chess", 2L, 10));
		challengeList.add(new Challenge(3L, LocalDateTime.now(), "Chess", 3L, 10));
		challengeList.add(new Challenge(4L, LocalDateTime.now(), "Chess", 1L, 10));
		challengeList.add(new Challenge(5L, LocalDateTime.now(), "Monopoly", 0L, 10));
		challengeList.add(new Challenge(6L, LocalDateTime.now(), "Chess", 0L, 10));
		challengeList.add(new Challenge(7L, LocalDateTime.now(), "Chess", 0L, 1));
	}

	public List<Challenge> getChallengeList() {
		return challengeList;
	}

	public void setChallengeList(List<Challenge> challengeList) {
		this.challengeList = challengeList;
	}
	
	public void addChallenge(Challenge challenge){
		this.challengeList.add(challenge);
	}

	@Override
	public List<Challenge> getPlayerAllChallengeHistory(Long playerId) {
		List<Challenge> playerChallengeHistory = new ArrayList<>();

		for (Challenge challenge : challengeList) {
			if (challenge.getPlayerId() == playerId) {
				playerChallengeHistory.add(challenge);
			}
		}
		return playerChallengeHistory;
	}

	@Override
	public List<Challenge> getPlayerOneGameChallengeHistory(Long playerId, String gameTitle) {
		List<Challenge> playerOneGameChallengeHistory = new ArrayList<>();

		for (Challenge challenge : challengeList) {
			if (challenge.getPlayerId() == playerId && challenge.getGameTitle() == gameTitle) {
				playerOneGameChallengeHistory.add(challenge);
			}
		}
		return playerOneGameChallengeHistory;
	}
}
