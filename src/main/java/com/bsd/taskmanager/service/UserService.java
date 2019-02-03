package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.repository.UserRepository;
import com.bsd.taskmanager.service.exception.UserNotFoundException;

@Service
public class UserService implements User {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {
		List<ValidationRule<UserDto>> createUserRules = UserValidationFactory.getCreateUserRules();

		validate(user, createUserRules);
		
		Users users = Users
						.builder()
							.username(user.getUsername())
							.firstName(user.getFirstName())
							.lastName(user.getLastName())
						.build();
		
		users = userRepository.save(users);

		return UserDto
					.builder()
						.username(users.getUsername())
						.firstName(users.getFirstName())
						.lastName(users.getLastName())
						.id(users.getId())
					.build();
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
		} else {
			throw new UserNotFoundException(id);
		}
		
		return userDto;
	}

	@Override
	public void updateUser(Long id, UserDto user) {
		List<ValidationRule<UserDto>> createUserRules = UserValidationFactory.getUpdateUserRules();

		validate(user, createUserRules);
		
		UserDto userDto = getUser(id);
		
		Users userEntity = Users
						.builder()
							.id(id)
							.username(userDto.getUsername())
							.firstName(user.getFirstName())
							.lastName(user.getLastName())
						.build();
		
		userRepository.save(userEntity);
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
	
	private void validate(UserDto user, List<ValidationRule<UserDto>> createUserRules) {
		for (ValidationRule<UserDto> rule : createUserRules) {
			rule.validate(user);
		}
	}
}
