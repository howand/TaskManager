package com.bsd.taskmanager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.taskmanager.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

	@Transactional
	void deleteByIdAndUserId(Long taskId, Long userId);
	
	Tasks getByIdAndUserId(Long taskId, Long userId);
	
	List<Tasks> getByUserId(Long userId);
	
	List<Tasks> getByStatus(String status);
}
