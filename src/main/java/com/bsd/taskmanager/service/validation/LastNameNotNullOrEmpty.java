package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.MandatoryFieldException;

public class LastNameNotNullOrEmpty implements ValidationRule<UserDto> {

	@Override
	public void validate(UserDto data) {
		if (data.getLastName() == null || data.getLastName().isEmpty()) {
			throw new MandatoryFieldException("Last Name");
		}
	}

}
