package com.bsd.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.taskmanager.entity.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

}
