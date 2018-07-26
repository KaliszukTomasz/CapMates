package com.example.game;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TestAspect.class);

	Long variableLong;

	// exec..(* com.test*.*(Iloscarg) -> (klasy metody, argumenty)"execution(*
	// com.example.game.controller.rest.TestRestController.*(..))")
	// @Before("execution(* com.example.game.repository.*.*(..))")
	// public void beforeRepo() {
	// LOGGER.info("StartTime" + Instant.now().toString());
	// variableLong = Instant.now().toEpochMilli();
	//
	// }
	// @After("execution(* com.example.game.repository.*.*(..))")
	// public void afterRepo() {
	// LOGGER.info("EndTime:" + Instant.now().toString());
	// Long newLong = Instant.now().toEpochMilli()-variableLong;
	// LOGGER.info("Method executed in " + newLong +"ms");
	//
	//
	// }
	//
	@Around("execution(* com.example.game.repository.*.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		LOGGER.info("Going to call the method." + pjp.getSignature().getName());
		Object output = pjp.proceed();
		LOGGER.info("Method execution completed.");
		long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Method execution time: " + elapsedTime + " milliseconds.");
		return output;
	}
}
