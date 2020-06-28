package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Task;
import com.pmrendszer.repository.TaskRepo;

@Service
public class TaskService {
	private TaskRepo taskRepo;
	private final int ACTIVE_STATUS_ID = 3;

	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	public List<Task> getActiveTasks() {
		return taskRepo.findByStatusId(ACTIVE_STATUS_ID);
	}

	public Task getTaskById(int id) throws EntityNotFoundException {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		return task;
	}

	public List<Task> getTasksByTopic(String topic) throws EntityNotFoundException {
		List<Task> tasks = taskRepo.findByTopicContainingOrderByTopic(topic);
		CheckerClass.ifEmptyThrowException(tasks);

		return tasks;
	}

	public void addTask(Task task) {
		taskRepo.save(task);
	}

	public void updateTask(int id, Task taskDetails) throws EntityNotFoundException {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		task.setTopic(taskDetails.getTopic());
		task.setDeadline(taskDetails.getDeadline());
		task.setTeamLeader(taskDetails.getTeamLeader());
		task.setProject(taskDetails.getProject());
		task.setStatus(taskDetails.getStatus());
		task.setDescription(taskDetails.getDescription());
		taskRepo.save(task);
	}

	public void deleteTask(int id) throws EntityNotFoundException {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		taskRepo.delete(task);
	}

	@Autowired
	public void setTaskRepo(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}
}