package com.bsd.taskmanager.service;

public interface ValidationRule<T> {

	void validate(T data);
}
