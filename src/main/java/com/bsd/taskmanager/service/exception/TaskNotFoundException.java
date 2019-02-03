package com.bsd.taskmanager.service.exception;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3032909431636192004L;

	public TaskNotFoundException(Long id) {
		super("Task not found: " + id);
	}
}
