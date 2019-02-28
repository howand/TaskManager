package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.service.validation.DateNotNull;
import com.bsd.taskmanager.service.validation.TaskNameNotNullOrEmpty;
import com.bsd.taskmanager.service.validation.TaskStatusPendingOrDone;

public class TaskValidationFactory {
	private static List<ValidationRule<TaskDto>> createTaskRules = new ArrayList<>();
	
	private TaskValidationFactory() {
		
	}
	
	public static List<ValidationRule<TaskDto>> getCreateTaskRules() {
		if (createTaskRules.isEmpty()) {
			createTaskRules.add(new TaskNameNotNullOrEmpty());
			createTaskRules.add(new DateNotNull());
			createTaskRules.add(new TaskStatusPendingOrDone());
		}
		
		return createTaskRules;
	}
}
