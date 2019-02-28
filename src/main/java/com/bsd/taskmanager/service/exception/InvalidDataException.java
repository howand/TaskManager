package com.bsd.taskmanager.service.exception;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1270422772615854817L;

	public InvalidDataException(String column) {
		super("Invalid data specified for column: " + column);
	}
}
