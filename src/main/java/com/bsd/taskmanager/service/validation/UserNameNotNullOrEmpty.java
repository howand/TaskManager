package com.bsd.taskmanager.service.validation;

import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.service.ValidationRule;
import com.bsd.taskmanager.service.exception.MandatoryFieldException;

public class UserNameNotNullOrEmpty implements ValidationRule<UserDto> {

	@Override
	public void validate(UserDto data) {
		if (data.getUsername() == null || data.getUsername().isEmpty()) {
			throw new MandatoryFieldException("username");
		}
	}

}
