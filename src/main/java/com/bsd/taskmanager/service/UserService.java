package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.repository.UserRepository;

@Service
public class UserService implements User {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {
		Users users = Users
						.builder()
							.username(user.getUsername())
							.firstName(user.getFirstName())
							.lastName(user.getLastName())
						.build();
		
		users = userRepository.save(users);
		
		user.setId(users.getId());
		
		return user;
	}

	@Override
	public List<UserDto> getUsers() {
		List<Users> users = userRepository.findAll();
		List<UserDto> userDto = new ArrayList<>();
		
		for (Users user : users) {
			userDto.add(buildUser(user));
		}
		
		return userDto;
	}
	
	@Override
	public UserDto getUser(Long id) {
		Optional<Users> optional = userRepository.findById(id);
		
		UserDto userDto = null;
		
		if (optional.isPresent()) {
			Users user = optional.get();
			userDto = buildUser(user);
		}
		
		return userDto;
	}

	@Override
	public boolean updateUser(Long id, String firstName, String lastName) {
		UserDto userDto = getUser(id);
		
		Users user = Users
						.builder()
							.id(id)
							.username(userDto.getUsername())
							.firstName(firstName)
							.lastName(lastName)
						.build();
		
		userRepository.save(user);
		
		return true;
	}
	
	private UserDto buildUser(Users user) {
		return UserDto
					.builder()
						.id(user.getId())
						.username(user.getUsername())
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
					.build();
	}
}
