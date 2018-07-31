package com.example.game.service;

import java.awt.image.Kernel;
import java.util.*;
import java.util.stream.Collectors;

import com.example.game.controller.RestResponseExceptionHandler;
import com.example.game.transferObjects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.enums.Status;
import com.example.game.entity.Challenge;
import com.example.game.entity.Game;
import com.example.game.entity.Player;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.Statistic;
import com.example.game.mappers.ChallengeHistoryMapper;
import com.example.game.mappers.GameTypeSetMapper;
import com.example.game.mappers.ProfilPlayerMapper;
import com.example.game.repository.ChallengeRepository;
import com.example.game.repository.GameTypeRepository;
import com.example.game.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ProfilPlayerMapper profilPlayerMapper;

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Autowired
    private ChallengeHistoryMapper challengeHistoryMapper;

    @Autowired
    private GameTypeSetMapper gameTypeListMapper;

    @Override
    public Statistic getStatistic(Long playerId) {
        return playerRepository.getPlayerStaistics(playerId);
    }

    @Override
    public Integer getMyLevel(Long playerId) {
        return playerRepository.getActualPlayerLevel(playerId);
    }


    private List<PlayerProfile> filterByFirstName(String firstName, List<PlayerProfile> playerProfileList) {
        if (null != firstName) {
            playerProfileList =
                    playerProfileList.stream().filter(profile ->
                            firstName.equals(profile.getFirstName()))
                            .collect(Collectors.toList());
        }
        return playerProfileList;
    }

    private List<PlayerProfile> filterByLastName(String lastName, List<PlayerProfile> playerProfileList) {
        if (null != lastName) {
            playerProfileList =
                    playerProfileList.stream().filter(profile ->
                            lastName.equals(profile.getLastName()))
                            .collect(Collectors.toList());
        }
        return playerProfileList;
    }

    private List<PlayerProfile> filterByEmail(String email, List<PlayerProfile> playerProfileList) {
        if (null != email) {
            playerProfileList =
                    playerProfileList.stream().filter(profile ->
                            email.equals(profile.getEmail())).collect(Collectors.toList());
        }
        return playerProfileList;
    }

    private List<PlayerProfile> filterByMotto(String motto, List<PlayerProfile> playerProfileList) {
        if (null != motto) {
            playerProfileList =
                    playerProfileList.stream().filter(profile ->
                            motto.equals(profile.getMotto())).collect(Collectors.toList());
        }
        return playerProfileList;
    }

    @Override
    public List<PlayerProfile> getPlayerProfilesByFilter(PlayerQuery profilFilter) {
        List<PlayerProfile> playerProfileList = getPlayerProfileList();
        playerProfileList = filterByFirstName(profilFilter.getFirstName(), playerProfileList);
        playerProfileList = filterByLastName(profilFilter.getLastName(), playerProfileList);
        playerProfileList = filterByEmail(profilFilter.getEmail(), playerProfileList);
        playerProfileList = filterByMotto(profilFilter.getMotto(), playerProfileList);

        if (playerProfileList.isEmpty()) {
            throw new NoSuchElementException();
        }

        return playerProfileList;
    }


    @Override
    public List<ChallengeTO> getMyChallengeHistory(Long playerId) {
        List<Challenge> challengeList = challengeRepository.getPlayerAllChallengeHistory(playerId);
        List<ChallengeTO> challengeToList = challengeHistoryMapper.mapChallengeHistory(challengeList);
        return challengeToList;
    }

    @Override
    public Set<GameTypeTO> getMyGames(Long playerId) {
        if (playerRepository.getPlayerGames(playerId) == null) {
            return new HashSet<>();
        }
        return gameTypeListMapper.mapToGameTypeTOSet(playerRepository.getPlayerGames(playerId));
    }

    @Override
    public Set<Game> getAllGamesInCollection() {
        return gameTypeRepository.getGamesContainer();
    }

    @Override
    public void eraseGameFromMyGames(Long playerId, String gameTitle) {
        playerRepository.eraseGameFromPlayerGames(playerId, gameTitle);
    }

    @Override
    public void addNewGameToMyGames(Long playerId, String gameTitle, Integer numberOfPlayers) {
        Game game = new Game();
        game.setNumberOfPlayers(numberOfPlayers);
        game.setTitle(gameTitle);
        playerRepository.addGameToPlayerGames(playerId, game);
        gameTypeRepository.addGameToGamesContainer(game);
    }

    @Override
    public PlayerProfile getMyProfile(Long playerId) {
        Player player = playerRepository.getPlayer(playerId);
        return profilPlayerMapper.mapToPlayerProfile(player);
    }

    @Override
    public PlayerProfile editMyProfile(Long playerId, PlayerQuery playerProfil) {
        Player player = playerRepository.getPlayer(playerId);
        player.setFirstName(playerProfil.getFirstName());
        player.setLastName(playerProfil.getLastName());
        player.setGames(playerProfil.getGames());
        player.setMotto(playerProfil.getMotto());

        playerRepository.editPlayer(player);
        return getMyProfile(playerId);
    }

    @Override
    public void changeMyPassword(Long playerId, PlayerProfile playerProfile) {
        Player player = playerRepository.getPlayer(playerId);
        player.setPassword(playerProfile.getPassword());
    }

    @Override
    public void addMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO) {

        PlayerAvailability availabilityTime = new PlayerAvailability();
        availabilityTime.setEndTime(availabilityTimeTO.getEndTime());
        availabilityTime.setStartTime(availabilityTimeTO.getStartTime());

        playerRepository.addHoursAvailability(availabilityTime, playerId);
    }

    @Override
    public void eraseMyAvailabilityTime(Long playerId, AvailabilityTimeTO availabilityTimeTO) {
        playerRepository.eraseHoursAvailability(availabilityTimeTO.getStartTime(), availabilityTimeTO.getEndTime(),
                playerId);

    }

    @Override
    public List<PlayerProfile> getPlayerProfileList() {
        List<PlayerProfile> playerProfiles = new ArrayList<>();
        for (Player player : playerRepository.getPlayers()) {
            playerProfiles.add(profilPlayerMapper.mapToPlayerProfile(player));
        }
        return playerProfiles;
    }

    @Override
    public void changeStatusToOfflineOnMyAvailabilityTimeAndLeaveMessage(Long playerId,
                                                                         AvailabilityTimeTO availabilityTimeTO, String message) {
        Player player = playerRepository.getPlayer(playerId);

        for (PlayerAvailability availability : player.getPlayerAvailabilityList()) {
            if (availability.getStartTime().equals(availabilityTimeTO.getStartTime())
                    && availability.getEndTime().equals(availabilityTimeTO.getEndTime())) {
                availability.setStatus(Status.OFFLINE);
                availability.setMessage(message);
            }

        }
    }
    // @Override
    // public Player login(String email, String password) {
    // Optional<Player> actualPlayer =
    // playerRepository.getPlayers().stream().filter(gamer ->
    // gamer.getEmail().equals(email))
    // .findFirst();
    // if (!actualPlayer.isPresent()) {
    // throw new UnauthorizedLoginException("Email doeasnt exist!");
    // }
    // Player player = actualPlayer.get();
    // if (!player.getPassword().equals(password)) {
    // throw new UnauthorizedLoginException("Password is incorrect!");
    // }
    //
    // return player;
    // }

}
