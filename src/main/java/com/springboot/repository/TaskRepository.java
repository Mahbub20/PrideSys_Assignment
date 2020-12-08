package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
