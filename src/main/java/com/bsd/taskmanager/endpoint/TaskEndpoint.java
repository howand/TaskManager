package com.bsd.taskmanager.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.service.Task;
import com.bsd.taskmanager.service.exception.TaskNotFoundException;
import com.bsd.taskmanager.service.exception.UserNotFoundException;

@RestController
@RequestMapping("/user")
public class TaskEndpoint {
	
	@Autowired
	private Task taskService;

	
	@PostMapping("/{id}/task")
	public ResponseEntity<Void> createTask(@PathVariable Long id, @RequestBody TaskDto task) {
		
		try {
			taskService.createTask(id, task);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/{id}/task/{task_id}")
	public ResponseEntity<Void> updateUserTask(@PathVariable("id") Long userId, @PathVariable("task_id") Long taskId, @RequestBody TaskDto task) {
		
		try {
			taskService.updateUserTask(userId, taskId, task);
		} catch (TaskNotFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}/task/{task_id}")
	public ResponseEntity<TaskDto> getUserTask(@PathVariable("id") Long userId, @PathVariable("task_id") Long taskId) {
		return new ResponseEntity<TaskDto>(taskService.getUserTask(userId, taskId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/task")
	public ResponseEntity<List<TaskDto>> getUserTasks(@PathVariable Long id) {
		return new ResponseEntity<List<TaskDto>>(taskService.getUserTasks(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{user_id}/task/{task_id}")
	public ResponseEntity<Void> deleteTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
		taskService.deleteTask(userId, taskId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
