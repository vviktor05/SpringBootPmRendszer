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
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.service.ProjectTeamService;

@RestController
@Validated
@RequestMapping("api/projects_teams")
public class ProjectTeamApiController {
	private ProjectTeamService projectTeamService;

	@GetMapping("")
	public List<ProjectTeam> getAllProjectsTeams() {
		return projectTeamService.getAllprojectsTeams();
	}

	@GetMapping("/project_id/{projectId}/team_id/{teamId}")
	public ProjectTeam getProjectTeamById(
			@PathVariable("projectId") @Min(value = 1, message = "{id.path.valid}") int projectId,
			@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId)
			throws EntityNotFoundException {

		return projectTeamService.getProjectTeamById(projectId, teamId);
	}

	@PostMapping("")
	public void addProjectTeam(@Valid @RequestBody ProjectTeam projectTeam) {
		projectTeamService.addProjectTeam(projectTeam);
	}

	@PutMapping("/project_id/{projectId}/team_id/{teamId}")
	public void updateProjectTeam(
			@PathVariable("projectId") @Min(value = 1, message = "{id.path.valid}") int projectId,
			@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId,
			@Valid @RequestBody ProjectTeam projectTeam) throws EntityNotFoundException {

		projectTeamService.updateProjectTeam(projectId, teamId, projectTeam);
	}

	@DeleteMapping("/project_id/{projectId}/team_id/{teamId}")
	public void deleteProjectTeam(
			@PathVariable("project_id") @Min(value = 1, message = "{id.path.valid}") int projectId,
			@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId)
			throws EntityNotFoundException {

		projectTeamService.deleteProjectTeam(projectId, teamId);
	}

	@Autowired
	public void setProjectTeamService(ProjectTeamService projectTeamService) {
		this.projectTeamService = projectTeamService;
	}
}