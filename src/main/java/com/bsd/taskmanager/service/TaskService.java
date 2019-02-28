package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.taskmanager.entity.Tasks;
import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.repository.TaskRepository;
import com.bsd.taskmanager.service.exception.TaskNotFoundException;

@Service
public class TaskService implements Task {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Long createTask(Long userId, TaskDto taskDto) {
		UserDto user = userService.getUser(userId);
		
		List<ValidationRule<TaskDto>> createTaskRules = TaskValidationFactory.getCreateTaskRules();
		
		validate(taskDto, createTaskRules);
			
		Tasks task = taskRepository.save(Tasks
				.builder()
					.name(taskDto.getName())
					.description(taskDto.getDescription())
					.dateTime(taskDto.getDateTime())
					.status("pending")
					.user(Users
							.builder()
								.id(user.getId())
								.firstName(user.getFirstName())
								.lastName(user.getLastName())
							.build())
				.build());
		
		return task.getId();
	}
	
	@Override
	public List<TaskDto> getUserTasks(Long userId) {
		List<Tasks> userTasks = taskRepository.getByUserId(userId);
		List<TaskDto> tasks = new ArrayList<>();
			
		return getTaskList(userTasks, tasks);
	}
	
	@Override
	public TaskDto getUserTask(Long userId, Long taskId) {
		Tasks userTask = taskRepository.getByIdAndUserId(taskId, userId);
		TaskDto task = TaskDto
							.builder()
								.id(userTask.getId())
								.name(userTask.getName())
								.description(userTask.getDescription())
								.dateTime(userTask.getDateTime())
							.build();
		
		return task;
	}

	@Override
	public void deleteTask(Long userId, Long taskId) {
		taskRepository.deleteByIdAndUserId(taskId, userId);
	}
	
	@Override 
	public void updateUserTask(Long userId, Long taskId, TaskDto taskDto) {
		Tasks userTask = taskRepository.getByIdAndUserId(taskId, userId);
		
		if (userTask == null) {
			throw new TaskNotFoundException(taskId);
		}
		userTask.setName(taskDto.getName());
		
		taskRepository.save(userTask);
	}
	
	@Override
	public List<TaskDto> getAllPendingTasks() {
		List<Tasks> pendingTasks = taskRepository.getByStatus("pending");
		List<TaskDto> tasks = new ArrayList<>();
		
		for (Tasks task : pendingTasks) {
			tasks.add(TaskDto
						.builder()
							.dateTime(task.getDateTime())
							.description(task.getDescription())
							.id(task.getId())
							.name(task.getName())
							.status(task.getStatus())
						.build());
		}
		
		return tasks;
	}
	
	@Override
	public void markTaskAsDone(Long id) {
		Tasks task = taskRepository.findById(id).get();
		
		task.setStatus("done");
		
		taskRepository.save(task);
	}
	
	private List<TaskDto> getTaskList(List<Tasks> userTasks, List<TaskDto> tasks) {
		for (Tasks task : userTasks) {
			tasks.add(TaskDto
						.builder()
							.id(task.getId())
							.name(task.getName())
							.description(task.getDescription())
							.dateTime(task.getDateTime())
							.status(task.getStatus())
						.build());
		}
		
		return tasks;
	}
	
	private void validate(TaskDto task, List<ValidationRule<TaskDto>> createTaskRules) {
		for (ValidationRule<TaskDto> taskRule : createTaskRules) {
			taskRule.validate(task);
		}
	}
}
