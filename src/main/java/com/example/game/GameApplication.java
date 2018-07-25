package com.example.game;

import com.example.game.entity.Player;
import com.example.game.repository.PlayerRepositoryImpl;
import com.example.game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy
public class GameApplication {

	
	public static void main(String[] args) {

		Player currentLoggedPlayer;

		

		//currentLoggerPlayer = lgoin/haslo

		//Podaj w od ktorej godziny chcesz grac
		//Podaj do ktorej godziny chcesz grac



		SpringApplication.run(GameApplication.class, args);
	}
}

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = CapmatesApplication.class)
//public class TestServiceTest {
//
//    @Autowired
//    TestService testService;
//
//    @Test
//    public void shouldTest() {
//        testService.test();
//    }
//} 
