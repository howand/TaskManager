package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.UserDto;

public interface User {

	UserDto createUser(UserDto user);
	
	List<UserDto> getUsers();
	
	UserDto getUser(Long id);
	
	boolean updateUser(Long id, String firstName, String lastName);
}
