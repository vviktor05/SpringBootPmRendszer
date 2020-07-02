package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Task;
import com.pmrendszer.service.TaskService;

@RestController
@Validated
@RequestMapping("/api")
public class TaskApiController {
	private TaskService taskService;

	@GetMapping("/project_manager/tasks")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/project_manager/tasks/active")
	public List<Task> getActiveTasks() {
		return taskService.getActiveTasks();
	}

	@GetMapping("/project_manager/tasks/id/{id}")
	public Task getTaskById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return taskService.getTaskById(id);
	}

	@GetMapping("/project_manager/tasks/topic/{topic}")
	public List<Task> getTasksByTopic(@PathVariable("topic") String topic) throws EntityNotFoundException {
		return taskService.getTasksByTopic(topic);
	}

	@PostMapping("/project_manager/tasks")
	public Task addTask(@Valid @RequestBody Task task) {
		return taskService.addTask(task);
	}

	@PutMapping("/project_manager/tasks/{id}")
	public Task updateTask(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Task taskDetails) throws EntityNotFoundException {

		return taskService.updateTask(id, taskDetails);
	}

	@DeleteMapping("/project_manager/tasks/{id}")
	public void deleteTask(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		
		taskService.deleteTask(id);
	}

	@GetMapping("/team_leader/tasks")
	public List<Task> getMyTeamLeaderTasks() {
		return taskService.getMyTeamLeaderTasks();
	}

	@GetMapping("/team_leader/tasks/active")
	public List<Task> getMyTeamLeaderActiveTasks() {
		return taskService.getMyTeamLeaderActiveTasks();
	}

	@GetMapping("/team_leader/tasks/id/{id}")
	public Task getMyTeamLeaderTaskById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return taskService.getMyTeamLeaderTaskById(id);
	}

	@GetMapping("/team_leader/tasks/topic/{topic}")
	public List<Task> getMyTeamLeaderTasksByTopic(@PathVariable("topic") String topic) throws EntityNotFoundException {
		return taskService.getMyTeamLeaderTasksByTopic(topic);
	}

	@GetMapping("/developer/tasks")
	public List<Task> getMyTasks() {
		return taskService.getMyDeveloperTasks();
	}

	@GetMapping("/developer/tasks/active")
	public List<Task> getMyActiveTasks() {
		return taskService.getMyDeveloperActiveTasks();
	}

	@GetMapping("/developer/tasks/id/{id}")
	public Task getMyTaskById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return taskService.getMyDeveloperTaskById(id);
	}

	@GetMapping("/developer/tasks/topic/{topic}")
	public List<Task> getMyTasksByTopic(@PathVariable("topic") String topic) throws EntityNotFoundException {
		return taskService.getMyDeveloperTasksByTopic(topic);
	}
	
	@PostMapping("/team_leader/tasks")
	public Task addMyTeamLeaderTask(@Valid @RequestBody Task task) throws Exception {
		return taskService.addMyTeamLeaderTask(task);
	}

	@PutMapping("/team_leader/tasks/{id}")
	public Task updateMyTeamLeaderTask(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Task taskDetails) throws Exception {

		return taskService.updateMyTeamLeaderTask(id, taskDetails);
	}

	@DeleteMapping("/team_leader/tasks/{id}")
	public void deleteMyTeamLeaderTask(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws Exception {
		
		taskService.deleteMyTeamLeaderTask(id);
	}
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
}