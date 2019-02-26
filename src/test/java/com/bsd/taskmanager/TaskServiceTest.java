package com.bsd.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.bsd.taskmanager.entity.Tasks;
import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.repository.TaskRepository;
import com.bsd.taskmanager.repository.UserRepository;
import com.bsd.taskmanager.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskManagerApplication.class)
public class TaskServiceTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskService taskService;
	
	private Users user = null;
	
	@Before
	public void setup() {
		user = Users
			.builder()
				.firstName("FirstName")
				.lastName("LastName")
				.username("Username")
			.build();
		
		user = userRepository.save(user);
	}

	@Test
	public void testAddTaskForUser() {
		TaskDto task = TaskDto
							.builder()
								.name("name")
								.description("description")
								.dateTime(LocalDateTime.now())
							.build();
		
		Long id = taskService.createTask(user.getId(), task);
		
		assertNotNull(id);
	}
	
	@Test
	public void testUpdateTaskForUser() {		
		Tasks task = createTask();
				
		TaskDto taskDto = TaskDto
				.builder()
					.dateTime(task.getDateTime())
					.description("description")
					.name("newName")
				.build();
		
		taskService.updateUserTask(user.getId(), task.getId(), taskDto);
		
		task = taskRepository.getByIdAndUserId(task.getId(), user.getId());
		
		assertEquals("newName", task.getName());
	}
	
	@Test
	public void testGetUserTask() {
		Tasks task = createTask();
		
		TaskDto retrievedTask = taskService.getUserTask(user.getId(), task.getId());
		
		assertEquals(task.getId(), retrievedTask.getId());
	}
	
	@Test
	public void testGetAllUserTasks() {
		createTask();
		
		List<TaskDto> retrievedTasks = taskService.getUserTasks(user.getId());
		
		assertEquals(1, retrievedTasks.size());
	}
	
	@Test
	public void testDeleteUserTask() {
		Tasks task = createTask();
		
		taskService.deleteTask(user.getId(), task.getId());
		
		List<Tasks> retrievedTasks = taskRepository.findAll(Sort.unsorted());
		
		assertEquals(0, retrievedTasks.size());
	}
	
	@After
	public void tearDown() {
		userRepository.deleteAll();
	}
	
	private Tasks createTask() {
		return taskRepository.save(Tasks
								.builder()
									.name("name")
									.description("description")
									.dateTime(LocalDateTime.now())
									.user(user)
								.build());
	}

}

