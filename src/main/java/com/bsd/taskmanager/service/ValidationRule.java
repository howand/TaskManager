package com.bsd.taskmanager.service;

public interface ValidationRule<T> {

	/**
	 * A generic method used to perform validation on the data model.
	 * @param data The data model to validate.
	 */
	void validate(T data);
}
