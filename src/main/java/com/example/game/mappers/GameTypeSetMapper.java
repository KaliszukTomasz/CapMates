package com.example.game.mappers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.game.entity.Game;
import com.example.game.transferObjects.GameTypeTO;

@Component
public class GameTypeSetMapper {

	public Set<GameTypeTO> mapToGameTypeTOSet(Set<Game> gameSet) {
		Set<GameTypeTO> gameTypeToSet = gameSet.stream().map(temp -> {
			GameTypeTO newObj = new GameTypeTO();
			newObj.setNumberOfPlayers(temp.getNumberOfPlayers());
			newObj.setTitle(temp.getTitle());
			return newObj;
		}).collect(Collectors.toSet());

		return gameTypeToSet;

	}

}
