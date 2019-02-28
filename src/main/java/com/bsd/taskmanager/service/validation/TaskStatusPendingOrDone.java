package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.InvalidDataException;

public class TaskStatusPendingOrDone implements ValidationRule<TaskDto>{

	public void validate(TaskDto data) {
		if (data.getStatus() != null && !data.getStatus().equals("pending") && data.getStatus().equals("done")) {
			throw new InvalidDataException("status");
		}
	}
}
