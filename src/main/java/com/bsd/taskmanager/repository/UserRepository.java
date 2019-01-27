package com.bsd.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.taskmanager.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
