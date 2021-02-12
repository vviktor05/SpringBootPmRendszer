package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.domain.Project;
import com.pmrendszer.domain.Task;
import com.pmrendszer.repository.TaskRepo;

@Service
public class TaskService {
	private TaskRepo taskRepo;
	private EmployeeService employeeService;
	private ProjectService projectService;
	private final int ACTIVE_STATUS_ID = 3;

	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	public List<Task> getActiveTasks() {
		return taskRepo.findByStatusId(ACTIVE_STATUS_ID);
	}

	public int getNumberOfActiveTasks() {
		return taskRepo.countTasksByStatusId(ACTIVE_STATUS_ID);
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

	public Task addTask(Task task) {
		return taskRepo.save(task);
	}

	public Task updateTask(int id, Task taskDetails) throws EntityNotFoundException {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		return taskRepo.save(updateTaskDetails(task, taskDetails));
	}

	public void deleteTask(int id) throws EntityNotFoundException {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		taskRepo.delete(task);
	}

	public List<Task> getMyTeamLeaderTasks() {
		return taskRepo.findMyTeamLeaderTasks(getAuthenticatedEmployee().getId());
	}

	public List<Task> getMyTeamLeaderActiveTasks() {
		return taskRepo.findMyTeamLeaderActiveTasks(ACTIVE_STATUS_ID, getAuthenticatedEmployee().getId());
	}

	public Task getMyTeamLeaderTaskById(int id) throws EntityNotFoundException {
		Task task = taskRepo.findTeamLeaderMyTaskById(id, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(task);

		return task;
	}

	public List<Task> getMyTeamLeaderTasksByTopic(String topic) throws EntityNotFoundException {
		List<Task> tasks = taskRepo.findMyTeamLeaderTasksByTopic(topic, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(tasks);

		return tasks;
	}

	public List<Task> getMyDeveloperTasks() {
		return taskRepo.findMyDeveloperTasks(getAuthenticatedEmployee().getId());
	}

	public List<Task> getMyDeveloperActiveTasks() {
		return taskRepo.findMyDeveloperActiveTasks(ACTIVE_STATUS_ID, getAuthenticatedEmployee().getId());
	}

	public Task getMyDeveloperTaskById(int id) throws EntityNotFoundException {
		Task task = taskRepo.findMyDeveloperTaskById(id, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(task);

		return task;
	}

	public List<Task> getMyDeveloperTasksByTopic(String topic) throws EntityNotFoundException {
		List<Task> tasks = taskRepo.findMyDeveloperTasksByTopic(topic, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(tasks);

		return tasks;
	}

	public Task addMyTeamLeaderTask(Task task) throws Exception {
		List<Project> myProjects = projectService.getMyProjects();

		if (myProjects.contains(task.getProject())) {
			task = taskRepo.save(task);
		} else {
			throw new Exception("Csak a saját projektekhez lehet hozzáadni feladatot!");
		}

		return task;
	}

	public Task updateMyTeamLeaderTask(int id, Task taskDetails) throws Exception {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		List<Task> myTasks = getMyTeamLeaderTasks();

		if (myTasks.contains(task)) {
			task = taskRepo.save(updateTaskDetails(task, taskDetails));
		} else {
			throw new Exception("Csak a saját feladatokat lehet szerkeszteni!");
		}

		return task;
	}

	public void deleteMyTeamLeaderTask(int id) throws Exception {
		Task task = taskRepo.findById(id);
		CheckerClass.ifEmptyThrowException(task);

		List<Task> myTasks = getMyTeamLeaderTasks();

		if (myTasks.contains(task)) {
			taskRepo.delete(task);
		} else {
			throw new Exception("Csak a saját feladatokat lehet törölni!");
		}

		taskRepo.delete(task);
	}

	private Employee getAuthenticatedEmployee() {
		return employeeService.getAuthenticatedEmployee();
	}

	private Task updateTaskDetails(Task task, Task taskDetails) {
		task.setTopic(taskDetails.getTopic());
		task.setDeadline(taskDetails.getDeadline());
		task.setTeamLeader(taskDetails.getTeamLeader());
		task.setProject(taskDetails.getProject());
		task.setStatus(taskDetails.getStatus());
		task.setDescription(taskDetails.getDescription());
		task.setUpdateMode(true);
		return task;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setTaskRepo(TaskRepo taskRepo) {
		this.taskRepo = taskRepo;
	}
}