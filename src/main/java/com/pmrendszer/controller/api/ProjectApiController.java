package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.ProjectService;

@RestController
@Validated
@RequestMapping("/api")
public class ProjectApiController {
	private ProjectService projectService;

	@GetMapping("/project_manager/projects")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@GetMapping("/project_manager/projects/active")
	public List<Project> getActiveProjects() {
		return projectService.getActiveProjects();
	}

	@GetMapping("/project_manager/projects/id/{id}")
	public Project getProjectById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		return projectService.getProjectById(id);
	}

	@GetMapping("/project_manager/projects/name/{name}")
	public List<Project> getProjectsByName(@PathVariable("name") String name) throws EntityNotFoundException {
		return projectService.getProjectsByName(name);
	}

	@GetMapping("/project_manager/projects/search")
	public List<Project> getProjectsByDetailedSearch(
			@RequestParam(value = "customerId", defaultValue = "-1") int customerId,
			@RequestParam(value = "developmentAreaId", defaultValue = "-1") int developmentAreaId,
			@RequestParam(value = "orderDateMin", defaultValue = "-1") String orderDateMin,
			@RequestParam(value = "orderDateMax", defaultValue = "-1") String orderDateMax,
			@RequestParam(value = "projectStatusId", defaultValue = "-1") int projectStatusId,
			@RequestParam(value = "priorityId", defaultValue = "-1") int priorityId,
			@RequestParam(value = "projectLeaderId", defaultValue = "-1") int projectLeaderId,
			@RequestParam(value = "statusId", defaultValue = "-1") int statusId) throws EntityNotFoundException {

		return projectService.getProcjetsByDetailedSearch(customerId, developmentAreaId, orderDateMin, orderDateMax,
				projectStatusId, priorityId, projectLeaderId, statusId);
	}

	@PostMapping("/project_manager/projects")
	public void addProject(@Valid @RequestBody Project project) {
		projectService.addProject(project);
	}

	@PutMapping("/project_manager/projects/{id}")
	public void updateProject(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Project projectDetails) throws EntityNotFoundException {

		projectService.updateProject(id, projectDetails);
	}

	@DeleteMapping("/project_manager/projects/{id}")
	public void deleteProject(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		projectService.deleteProject(id);
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}