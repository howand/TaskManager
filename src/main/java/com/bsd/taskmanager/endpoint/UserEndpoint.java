package com.bsd.taskmanager.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.service.User;
import com.bsd.taskmanager.service.exception.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class UserEndpoint {
	
	@Autowired
	private User userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		user = userService.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<List<UserDto>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
		UserDto user = null;

		try {
			user = userService.getUser(id);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
		
		try {
			userService.updateUser(id, user);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
