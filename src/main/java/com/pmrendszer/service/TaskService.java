package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Task;
import com.pmrendszer.repository.TaskRepo;

@Service
public class TaskService {
	private TaskRepo taskRepo;
	private final int ACTIVE_STATUS_ID = 3;
	
	public List<Task> getAllTasks(){
		return taskRepo.findAll();
	}
	
	public List<Task> getActiveTasks(){
		return taskRepo.findByStatusId(ACTIVE_STATUS_ID);
	}
	
	public Task getTaskById(int id) {
		return taskRepo.findById(id);
	}
	
	public List<Task> getTasksByTopic(String topic){
		return taskRepo.findByTopicContainingOrderByTopic(topic);
	}
	
	@Autowired
	public void setTaskRepo(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}
}