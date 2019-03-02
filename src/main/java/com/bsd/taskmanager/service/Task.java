package com.bsd.taskmanager.service;

import java.util.List;

import com.bsd.taskmanager.model.TaskDto;

public interface Task {
	
	/**
	 * Creates a task for the specified user.
	 * @param userId The ID of the user for which the task should be created.
	 * @param task The task information to be created for the user.
	 * @return The ID of the task if created successfully.
	 */
	Long createTask(Long userId, TaskDto task);
	
	/**
	 * Return all tasks associated to the user.
	 * @param userId The ID of the user for which all tasks should be returned.
	 * @return A List of tasks associated to the user.
	 */
	List<TaskDto> getUserTasks(Long userId);
	
	/**
	 * Deletes the task associated to the user, based on the user ID and task ID.
	 * @param userId The ID of the user for which the task should be deleted.
	 * @param taskId The ID of the task to delete.
	 */
	void deleteTask(Long userId, Long taskId);
	
	/**
	 * Returns a single task for the specified user.
	 * @param userId The ID of the user to which the task belongs.
	 * @param taskId The ID of the task that should be returned.
	 * @return A task object containing the task information.
	 */
	TaskDto getUserTask(Long userId, Long taskId);
	
	/**
	 * Updates the specified task for the specified user.
	 * @param userId The ID of the user for which the task should be updated.
	 * @param taskId The ID of the task to update
	 * @param taskDto The task object containing the updated values.
	 */
	void updateUserTask(Long userId, Long taskId, TaskDto taskDto);
	
	/**
	 * Returns all pending tasks, regardless of the user to which they belong.
	 * @return A List containing all pending tasks.
	 */
	List<TaskDto> getAllPendingTasks();
	
	/**
	 * Changes the status of a task from PENDING to DONE.
	 * @param id The ID of the task for which the status should be changed.
	 */
	void markTaskAsDone(Long id);
}
