package com.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.models.Task;
import com.springboot.repository.TaskRepository;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	public List<Task> taskList(){
		
	  return taskRepo.findAll();
	}
	
	public void save(Task task) {
		taskRepo.save(task);
	}
	
	public Task get(Long id) {
		return taskRepo.findById(id).get();
	}
	
	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
	}

}
