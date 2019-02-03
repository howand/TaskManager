package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.MandatoryFieldException;

public class TaskNameNotNullOrEmpty implements ValidationRule<TaskDto>{

	public void validate(TaskDto data) {
		if (data.getName() == null || data.getName().isEmpty()) {
			throw new MandatoryFieldException("name");
		}
	}
}
