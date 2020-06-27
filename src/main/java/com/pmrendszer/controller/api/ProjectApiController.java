package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {
	private ProjectService projectService;

	@GetMapping("")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/active")
	public List<Project> getActiveProjects() {
		return projectService.getActiveProjects();
	}

	@GetMapping("/id/{id}")
	public Project getProjectById(@PathVariable("id") int id) {
		return projectService.getProjectById(id);
	}

	@GetMapping("/name/{name}")
	public List<Project> getProjectsByName(@PathVariable("name") String name) {
		return projectService.getProjectsByName(name);
	}

	@GetMapping("/search")
	public List<Project> getProjectsByDetailedSearch(
			@RequestParam(value = "customerId", defaultValue = "-1") int customerId,
			@RequestParam(value = "developmentAreaId", defaultValue = "-1") int developmentAreaId,
			@RequestParam(value = "orderDateMin", defaultValue = "-1") String orderDateMin,
			@RequestParam(value = "orderDateMax", defaultValue = "-1") String orderDateMax,
			@RequestParam(value = "projectStatusId", defaultValue = "-1") int projectStatusId,
			@RequestParam(value = "priorityId", defaultValue = "-1") int priorityId,
			@RequestParam(value = "projectLeaderId", defaultValue = "-1") int projectLeaderId,
			@RequestParam(value = "statusId", defaultValue = "-1") int statusId) {

		return projectService.getProcjetsByDetailedSearch(customerId, developmentAreaId, orderDateMin, orderDateMax,
				projectStatusId, priorityId, projectLeaderId, statusId);
	}

	@PostMapping("")
	public void addProject(@Valid @RequestBody Project project) {
		projectService.addProject(project);
	}

	@PutMapping("/{id}")
	public void updateProject(@PathVariable(value = "id") int id, @Valid @RequestBody Project projectDetails) {
		Project project = projectService.getProjectById(id);
		if (project == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nincs ilyen azonosítóval projekt: " + id);
		}
		projectService.updateProject(project, projectDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable(value = "id") int id) {
		Project project = projectService.getProjectById(id);
		if (project == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nincs ilyen azonosítóval projekt: " + id);
		}
		projectService.deleteProject(project);
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}