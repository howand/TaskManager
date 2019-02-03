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

@RestController
@RequestMapping("/user")
public class UserEndpoint {
	
	@Autowired
	private User userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		userDto = userService.createUser(userDto);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<List<UserDto>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
		return new ResponseEntity<UserDto>(userService.getUser(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
		userService.updateUser(id, userDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
