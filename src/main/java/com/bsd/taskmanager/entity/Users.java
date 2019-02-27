package com.bsd.taskmanager.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
@Table(name = "users")
public class Users {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username", unique = true)
	@Size(max = 50)
	private String username;
	
	@Column(name = "first_name")
	@Size(max = 50)
	private String firstName;
	
	@Column(name = "last_name")
	@Size(max = 50)
	private String lastName;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Tasks> tasks;
}
