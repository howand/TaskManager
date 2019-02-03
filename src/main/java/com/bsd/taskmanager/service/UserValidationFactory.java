package com.bsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.bsd.taskmanager.model.UserDto;
import com.bsd.taskmanager.service.validation.FirstNameNotNullOrEmpty;
import com.bsd.taskmanager.service.validation.LastNameNotNullOrEmpty;
import com.bsd.taskmanager.service.validation.UserNameNotNullOrEmpty;

public class UserValidationFactory {

	private static List<ValidationRule<UserDto>> createUserRules = new ArrayList<>();
	private static List<ValidationRule<UserDto>> updateUserRules = new ArrayList<>();
	
	private UserValidationFactory() {
		
	}
	
	public static List<ValidationRule<UserDto>> getCreateUserRules() {
		if (createUserRules.isEmpty()) {
			createUserRules.add(new UserNameNotNullOrEmpty());
			createUserRules.add(new FirstNameNotNullOrEmpty());
			createUserRules.add(new LastNameNotNullOrEmpty());
		}
		
		return createUserRules;
	}
	
	public static List<ValidationRule<UserDto>> getUpdateUserRules() {
		if (updateUserRules.isEmpty()) {
			updateUserRules.add(new FirstNameNotNullOrEmpty());
			updateUserRules.add(new LastNameNotNullOrEmpty());
		}
		
		return updateUserRules;
	}
}
