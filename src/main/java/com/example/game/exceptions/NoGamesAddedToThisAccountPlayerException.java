package com.example.game.exceptions;

public class NoGamesAddedToThisAccountPlayerException extends RuntimeException {

	private static final long serialVersionUID = 4855263689733019755L;

	public NoGamesAddedToThisAccountPlayerException(String message) {
		super(message);

	}

}
