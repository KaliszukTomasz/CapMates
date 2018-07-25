package com.example.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.core.api.AssertDelegateTarget;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.game.entity.Game;
import com.example.game.repository.GameTypeRepositoryImpl;
import com.example.game.repository.PlayerRepositoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameApplicationTests {

	@Autowired
	GameTypeRepositoryImpl gameTypeRepository;

	@Autowired
	PlayerRepositoryImpl playerRepositoryImpl;
	
	@Test
	public void contextLoads() {
	}

	

	

}
