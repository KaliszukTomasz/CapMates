package com.example.game.mappers;

import com.example.game.transferObjects.PlayerProfileBuilder;
import org.springframework.stereotype.Component;

import com.example.game.entity.Player;
import com.example.game.transferObjects.PlayerProfile;

@Component
public class ProfilPlayerMapper {

	public PlayerProfile mapToPlayerProfile(Player player) {

		PlayerProfile playerProfil = new PlayerProfile();

		playerProfil.setId(player.getId());
		playerProfil.setFirstName(player.getFirstName());
		playerProfil.setLastName(player.getLastName());
		playerProfil.setEmail(player.getEmail());
		playerProfil.setLevel(player.getLevel());
		playerProfil.setStatistic(player.getStatistic());
		playerProfil.setGames(player.getGames());
		playerProfil.setMotto(player.getMotto());
		playerProfil.setPlayerAvailabilityList(player.getPlayerAvailabilityList());
		
		return playerProfil;
	}
}
