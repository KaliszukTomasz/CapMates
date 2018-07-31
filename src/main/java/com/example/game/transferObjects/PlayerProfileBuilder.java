package com.example.game.transferObjects;

import com.example.game.entity.Game;
import com.example.game.entity.PlayerAvailability;
import com.example.game.entity.PlayerLevel;
import com.example.game.entity.Statistic;

import java.util.List;
import java.util.Set;

public class PlayerProfileBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String motto;
    private Statistic statistic;
    private PlayerLevel level;
    private String password;
    private List<PlayerAvailability> playerAvailabilityList;
    private Set<Game> games;

    public PlayerProfileBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public PlayerProfileBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PlayerProfileBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerProfileBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public PlayerProfileBuilder setMotto(String motto) {
        this.motto = motto;
        return this;
    }

    public PlayerProfileBuilder setStatistic(Statistic statistic) {
        this.statistic = statistic;
        return this;
    }

    public PlayerProfileBuilder setLevel(PlayerLevel level) {
        this.level = level;
        return this;
    }

    public PlayerProfileBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public PlayerProfileBuilder setPlayerAvailabilityList(List<PlayerAvailability> playerAvailabilityList) {
        this.playerAvailabilityList = playerAvailabilityList;
        return this;
    }

    public PlayerProfileBuilder setGames(Set<Game> games) {
        this.games = games;
        return this;
    }

    public PlayerProfile build() {
        return new PlayerProfile(id, firstName, lastName, email, motto, statistic, level, password, playerAvailabilityList, games);
    }
}