package com.example.game;

import com.example.game.entity.Player;
import com.example.game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {

		Player currentLoggedPlayer;


		//currentLoggerPlayer = lgoin/haslo

		//Podaj w od ktorej godziny chcesz grac
		//Podaj do ktorej godziny chcesz grac



		SpringApplication.run(GameApplication.class, args);
	}
}
