package com.example.game.exceptions;

public class UserNotFondException extends RuntimeException {

   	private static final long serialVersionUID = -3915422114452696280L;

	public UserNotFondException(String message) {
        super(message);
    }
}
