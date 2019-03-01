package com.bsd.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bsd.taskmanager.model.TaskDto;

@Component
public class TaskUpdater {

	@Autowired
	TaskService taskService;
	
	@Scheduled(cron = "${com.bsd.taskmanager.taskupdater.schedule}")
	public void updateTasks() {
		List<TaskDto> pendingTasks = taskService.getAllPendingTasks();
		
		for (TaskDto task : pendingTasks) {
			if (task.getDateTime().isBefore(LocalDateTime.now())) {
				System.out.println(task.getId() + "|" + task.getName() + "|" + task.getStatus() + "|"
						+ task.getDescription() + "|" + task.getDateTime());
				
				taskService.markTaskAsDone(task.getId());
			}
		}
	}
}
