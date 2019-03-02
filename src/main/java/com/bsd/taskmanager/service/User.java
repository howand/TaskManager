package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.UserDto;

public interface User {

	/**
	 * Creates a new user.
	 * @param user A UserDto object containing all the user information.
	 * @return A UserDto object containing additional user information.
	 */
	UserDto createUser(UserDto user);
	
	/**
	 * Returns all users.
	 * @return A List containing all the users.
	 */
	List<UserDto> getUsers();
	
	/**
	 * Returns a single user based on the users ID.
	 * @param id The ID of the user to return.
	 * @return A UserDto object containing the user information.
	 */
	UserDto getUser(Long id);
	
	/**
	 * Updates the specified user.
	 * @param id The ID of the user to update.
	 * @param user A UserDto object containing the updated information.
	 */
	void updateUser(Long id, UserDto user);
}
