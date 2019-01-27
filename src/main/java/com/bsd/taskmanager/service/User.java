package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.model.UserDto;

public interface User {

	UserDto createUser(UserDto user);
	
	List<UserDto> getUsers();
	
	UserDto getUser(Long id);
	
	boolean updateUser(Long id, String firstName, String lastName);
	
	boolean createTask(Long userId, TaskDto task);
	
	List<TaskDto> getUserTasks(Long userId);
	
	boolean deleteTask(Long taskId);
}
