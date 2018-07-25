package com.example.game.exceptions;

public class InvalidNameOfTheSearchedGameException extends RuntimeException {

	private static final long serialVersionUID = 6864464285489293474L;

	public InvalidNameOfTheSearchedGameException(String message) {
		super(message);
	}

}
