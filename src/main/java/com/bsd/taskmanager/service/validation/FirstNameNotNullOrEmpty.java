package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.MandatoryFieldException;

public class FirstNameNotNullOrEmpty implements ValidationRule<UserDto> {

	@Override
	public void validate(UserDto data) {
		if (data.getFirstName() == null || data.getFirstName().isEmpty()) {
			throw new MandatoryFieldException("First Name");
		}
	}

}
