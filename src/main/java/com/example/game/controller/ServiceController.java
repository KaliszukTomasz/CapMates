package com.example.game.controller;

import com.example.game.entity.Player;
import com.example.game.service.PlayerService;
import com.example.game.transferObjects.PlayerProfile;
import com.example.game.transferObjects.PlayerQuery;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public List<PlayerProfile> getAllPlayers(){
        return playerService.getPlayerProfileList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlayerProfile getPlayerProfile(@PathVariable("id")  Long id){
        return playerService.getMyProfile(id);
    }


    @RequestMapping( method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editProfilPlayer(@RequestBody PlayerQuery playerQuery){
        playerService.editMyProfile(playerQuery.getId(), playerQuery);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PlayerProfile> filterPlayers(@RequestBody PlayerQuery playerQuery){
       return playerService.getPlayerProfilesByFilter(playerQuery);
    }
}
