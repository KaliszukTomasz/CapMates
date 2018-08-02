package com.example.game.controller;

import com.example.game.entity.Player;
import com.example.game.service.PlayerService;
import com.example.game.transferObjects.PlayerProfile;
import com.example.game.transferObjects.PlayerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/players")
public class ServiceController {

    @Autowired
    PlayerService playerService;

    /**
     * Response to query get /players
     * get all players from database
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerProfile> getAllPlayers(){
        return playerService.getPlayerProfileList();
    }

    /**
     * Response to query get /players/ID
     * get user with userId = ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PlayerProfile getPlayerProfile(@PathVariable("id")  Long id){
        return playerService.getMyProfile(id);
    }

    /**
     * Response to query put /players
     * consumes = JSON_TYPE
     * edit players attributes values
     * @param playerQuery
     */
    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void editProfilPlayer(@RequestBody PlayerQuery playerQuery){
        playerService.editMyProfile(playerQuery.getId(), playerQuery);
    }

    /**
     * Respons to query put /players/filter
     * consumes = JSON_TYPE
     * filter from all players, players which are in criteria from put JSON file
     * @param playerQuery
     * @return
     */
    @RequestMapping(value = "/filter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<PlayerProfile> filterPlayers(@RequestBody PlayerQuery playerQuery){
       return playerService.getPlayerProfilesByFilter(playerQuery);
    }
}
