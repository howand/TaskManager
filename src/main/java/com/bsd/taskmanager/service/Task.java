package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.TaskDto;

public interface Task {
	boolean createTask(Long userId, TaskDto task);
	
	List<TaskDto> getUserTasks(Long userId);
	
	boolean deleteTask(Long userId, Long taskId);
	
	TaskDto getUserTask(Long userId, Long taskId);
	
	void updateUserTask(Long userId, Long taskId, TaskDto taskDto);
}
