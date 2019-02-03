package com.bsd.taskmanager.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bsd.taskmanager.TaskManagerApplication;
import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskManagerApplication.class)
public class UserServiceTest {
	
	@Autowired
	User userService;
	
	@Autowired
	UserRepository userRepository;

	@After
	public void tearDown() throws Exception {
		List<Users> users = userRepository.findAll();
		
		for (Users user : users) {
			userRepository.delete(user);
		}
	}

	@Test
	public void testCreateUser() {
		UserDto user = userService.createUser(UserDto
								.builder()
									.username("testUser")
									.firstName("testFirstName")
									.lastName("testLastName")
								.build());
		
		Optional<Users> result = userRepository.findById(user.getId());
		
		assertTrue(result.isPresent());
	}

	@Test
	public void testGetUsers() {
		Users firstUser = Users
								.builder()
									.username("testUser1")
									.firstName("testFirstName1")
									.lastName("testLastName1")
								.build();
		
		Users secondUser = Users
				.builder()
					.username("testUser2")
					.firstName("testFirstName2")
					.lastName("testLastName2")
				.build();
		
		userRepository.save(firstUser);
		userRepository.save(secondUser);
		
		List<UserDto> userDtos = userService.getUsers();
		
		assertEquals(2, userDtos.size());
	}

	@Test
	public void testGetUser() {
		Users firstUser = Users
				.builder()
					.username("testUser1")
					.firstName("testFirstName1")
					.lastName("testLastName1")
				.build();
		
		Users user = userRepository.save(firstUser);
		
		UserDto userDto = userService.getUser(user.getId());
		
		assertEquals("testUser1", userDto.getUsername());
	}

	@Test
	public void testUpdateUser() {
		Users firstUser = Users
				.builder()
					.username("testUser1")
					.firstName("testFirstName1")
					.lastName("testLastName1")
				.build();

		Users user = userRepository.save(firstUser);
		
		userService.updateUser(user.getId(), UserDto.builder().firstName("updateFirstName").lastName("updateLastName").build());
		
		user = userRepository.findById(user.getId()).get();
		
		assertEquals("updateFirstName", user.getFirstName());
		assertEquals("updateLastName", user.getLastName());
	}

}
