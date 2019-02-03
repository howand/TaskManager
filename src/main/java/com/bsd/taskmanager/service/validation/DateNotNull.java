package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.TaskDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.MandatoryFieldException;

public class DateNotNull implements ValidationRule<TaskDto> {

	public void validate(TaskDto data) {
		if (data.getDateTime() == null) {
			throw new MandatoryFieldException("date_time");
		}
	}
}
