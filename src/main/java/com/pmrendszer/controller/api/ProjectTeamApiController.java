package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.domain.Team;
import com.pmrendszer.service.ProjectTeamService;

@RestController
@RequestMapping("api/projects_teams")
public class ProjectTeamApiController {
	private ProjectTeamService projectTeamService;

	@GetMapping("")
	public List<ProjectTeam> getAllProjectsTeams() {
		return projectTeamService.getAllprojectsTeams();
	}

	@GetMapping("/working_on/project_id/{id}")
	public List<Team> getTeamsWorkingOnProject(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		return projectTeamService.getTeamsWorkingOnProject(id);
	}

	@GetMapping("/not_working_on/project_id/{id}")
	public List<Team> getTeamsNotWorkingOnProject(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		return projectTeamService.getTeamsNotWorkingOnProject(id);
	}

	@Autowired
	public void setProjectTeamService(ProjectTeamService projectTeamService) {
		this.projectTeamService = projectTeamService;
	}
}