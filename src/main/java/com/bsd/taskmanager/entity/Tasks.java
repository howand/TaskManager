package com.bsd.taskmanager.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.bsd.taskmanager.constants.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tasks")
public class Tasks {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	@Size(max = 50)
	private String name;
	
	@Column(name = "description")
	@Size(max = 500)
	private String description;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "status")
	private TaskStatus status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;
}
