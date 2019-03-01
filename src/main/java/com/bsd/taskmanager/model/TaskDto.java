package com.bsd.taskmanager.model;

import java.time.LocalDateTime;

import com.bsd.taskmanager.constants.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty(access = Access.READ_ONLY, value = "status")
	private TaskStatus status;

	@JsonProperty("date_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
}