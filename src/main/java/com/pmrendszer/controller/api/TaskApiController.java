package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Task;
import com.pmrendszer.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController {
	private TaskService taskService;

	@RequestMapping("")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	@RequestMapping("/active")
	public List<Task> getActiveTasks() {
		return taskService.getActiveTasks();
	}

	@RequestMapping("/id/{id}")
	public Task getTaskById(@PathVariable("id") int id) {
		return taskService.getTaskById(id);
	}
	
	@RequestMapping("/name/{topic}")
	public List<Task> getTasksByTopic(@PathVariable("topic") String topic) {
		return taskService.getTasksByTopic(topic);
	}
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}