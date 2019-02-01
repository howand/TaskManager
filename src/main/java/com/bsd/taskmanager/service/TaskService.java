package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.taskmanager.entity.Tasks;
import com.bsd.taskmanager.entity.Users;
import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.repository.TaskRepository;
import com.bsd.taskmanager.repository.UserRepository;

@Service
public class TaskService implements Task {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean createTask(Long userId, TaskDto taskDto) {
		Optional<Users> optional = userRepository.findById(userId);
		
		if (optional.isPresent()) {
			Users user = optional.get();
			
			taskRepository.save(Tasks
					.builder()
						.name(taskDto.getName())
						.description(taskDto.getDescription())
						.dateTime(taskDto.getDateTime())
						.user(user)
					.build());
		}
		
		return true;
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
	public boolean deleteTask(Long userId, Long taskId) {
		if (taskRepository.findById(taskId).isPresent()) {
			taskRepository.deleteByIdAndUserId(taskId, userId);
		}
		
		return true;
	}
	
	@Override 
	public void updateUserTask(Long userId, Long taskId, TaskDto taskDto) {
		Tasks userTask = taskRepository.getByIdAndUserId(taskId, userId);
		
		userTask.setName(taskDto.getName());
		
		taskRepository.save(userTask);
	}
	
	private List<TaskDto> getTaskList(List<Tasks> userTasks, List<TaskDto> tasks) {
		for (Tasks task : userTasks) {
			tasks.add(TaskDto
						.builder()
							.id(task.getId())
							.name(task.getName())
							.description(task.getDescription())
							.dateTime(task.getDateTime())
						.build());
		}
		
		return tasks;
	}
}
