package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.UserDto;

public interface User {

	UserDto createUser(UserDto user);
	
	List<UserDto> getUsers();
	
	UserDto getUser(Long id);
	
	void updateUser(Long id, UserDto user);
}
