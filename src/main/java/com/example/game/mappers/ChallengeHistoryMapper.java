package com.example.game.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.game.entity.Challenge;
import com.example.game.transferObjects.ChallengeTO;

@Component
public class ChallengeHistoryMapper {

	public List<ChallengeTO> mapChallengeHistory(List<Challenge> challengeList) {

		List<ChallengeTO> challengeToList = new ArrayList<>();

		challengeToList = challengeList.stream().map(temp -> {
			ChallengeTO newObj = new ChallengeTO();
			newObj.setDate(temp.getDate());
			newObj.setGameTitle(temp.getGameTitle());
			newObj.setPlayerId(temp.getPlayerId());
			newObj.setScore(temp.getScore());
			return newObj;
		}).collect(Collectors.toList());

		return challengeToList;
	}
}
