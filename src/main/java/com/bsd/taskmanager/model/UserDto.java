package com.bsd.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
}
