package com.example.game.exceptions;

public class SuchPlayerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 8005428071753208244L;

	public SuchPlayerAlreadyExistsException(String message) {
		super(message);
	}

}
