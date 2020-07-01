package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import javax.validation.constraints.Pattern;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.CheckerClass;
import com.pmrendszer.service.ProjectService;

@RestController
@Validated
@RequestMapping("/api")
public class ProjectApiController {
	private ProjectService projectService;
	private final String PROJECT_MANAGER = "ROLE_PROJECT_MANAGER";
	private final String TEAM_LEADER = "ROLE_TEAM_LEADER";

	@Secured({ PROJECT_MANAGER, TEAM_LEADER })
	@GetMapping("/projects")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}

	@Secured({ PROJECT_MANAGER, TEAM_LEADER })
	@GetMapping("/projects/active")
	public List<Project> getActiveProjects() {
		return projectService.getActiveProjects();
	}

	@Secured({ PROJECT_MANAGER, TEAM_LEADER })
	@GetMapping("/projects/id/{id}")
	public Project getProjectById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		return projectService.getProjectById(id);
	}

	@Secured({ PROJECT_MANAGER, TEAM_LEADER })
	@GetMapping("/projects/name/{name}")
	public List<Project> getProjectsByName(@PathVariable("name") String name) throws EntityNotFoundException {
		return projectService.getProjectsByName(name);
	}

	@Secured({ PROJECT_MANAGER, TEAM_LEADER })
	@GetMapping("/projects/search")
	public List<Project> getProjectsByDetailedSearch(
			@RequestParam(value = "customerId", defaultValue = "-1") int customerId,
			@RequestParam(value = "developmentAreaId", defaultValue = "-1") int developmentAreaId,
			@RequestParam(value = "orderDateMin", defaultValue = "1900-01-01") @Pattern(regexp = CheckerClass.DATE_REGEX, message = "{date.valid}") String orderDateMin,
			@RequestParam(value = "orderDateMax", defaultValue = "2300-01-01") @Pattern(regexp = CheckerClass.DATE_REGEX, message = "{date.valid}") String orderDateMax,
			@RequestParam(value = "projectStatusId", defaultValue = "-1") int projectStatusId,
			@RequestParam(value = "priorityId", defaultValue = "-1") int priorityId,
			@RequestParam(value = "projectLeaderId", defaultValue = "-1") int projectLeaderId,
			@RequestParam(value = "statusId", defaultValue = "-1") int statusId)
			throws EntityNotFoundException, Exception {

		return projectService.getProcjetsByDetailedSearch(customerId, developmentAreaId, orderDateMin, orderDateMax,
				projectStatusId, priorityId, projectLeaderId, statusId);
	}

	@Secured(PROJECT_MANAGER)
	@PostMapping("/projects")
	public Project addProject(@Valid @RequestBody Project project) {
		return projectService.addProject(project);
	}

	@Secured(PROJECT_MANAGER)
	@PutMapping("/projects/{id}")
	public Project updateProject(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Project projectDetails) throws EntityNotFoundException {
		return projectService.updateProject(id, projectDetails);
	}

	@Secured(PROJECT_MANAGER)
	@DeleteMapping("/projects/{id}")
	public void deleteProject(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		projectService.deleteProject(id);
	}

	@GetMapping("/developer/projects")
	public List<Project> getMyProjects() {
		return projectService.getMyProjects();
	}

	@GetMapping("/developer/projects/active")
	public List<Project> getMyActiveProjects() {
		return projectService.getMyActiveProjects();
	}

	@GetMapping("/developer/projects/id/{id}")
	public Project getMyProjectById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		return projectService.getMyProjectById(id);
	}

	@GetMapping("/developer/projects/name/{name}")
	public List<Project> getMyProjectsByName(@PathVariable("name") String name) throws EntityNotFoundException {
		return projectService.getMyProjectsByName(name);
	}

	@GetMapping("/developer/projects/search")
	public List<Project> getMyProjectsByDetailedSearch(
			@RequestParam(value = "customerId", defaultValue = "-1") int customerId,
			@RequestParam(value = "developmentAreaId", defaultValue = "-1") int developmentAreaId,
			@RequestParam(value = "orderDateMin", defaultValue = "1900-01-01") @Pattern(regexp = CheckerClass.DATE_REGEX, message = "{date.valid}") String orderDateMin,
			@RequestParam(value = "orderDateMax", defaultValue = "2300-01-01") @Pattern(regexp = CheckerClass.DATE_REGEX, message = "{date.valid}") String orderDateMax,
			@RequestParam(value = "projectStatusId", defaultValue = "-1") int projectStatusId,
			@RequestParam(value = "priorityId", defaultValue = "-1") int priorityId,
			@RequestParam(value = "projectLeaderId", defaultValue = "-1") int projectLeaderId,
			@RequestParam(value = "statusId", defaultValue = "-1") int statusId)
			throws EntityNotFoundException, Exception {

		return projectService.getMyProcjetsByDetailedSearch(customerId, developmentAreaId, orderDateMin, orderDateMax,
				projectStatusId, priorityId, projectLeaderId, statusId);
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}