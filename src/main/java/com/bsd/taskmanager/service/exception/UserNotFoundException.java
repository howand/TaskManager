package com.bsd.taskmanager.service.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3032909431636192004L;

	public UserNotFoundException(Long id) {
		super("User not found: " + id);
	}
}
