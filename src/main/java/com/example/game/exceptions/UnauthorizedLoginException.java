package com.example.game.exceptions;

public class UnauthorizedLoginException extends RuntimeException{

	private static final long serialVersionUID = -4004064220392887986L;

	public UnauthorizedLoginException(String message) {
		super(message);
	}
	
	
	
}
