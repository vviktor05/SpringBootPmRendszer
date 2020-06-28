package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.service.ProjectTeamService;

@RestController
@RequestMapping("api/projects_teams")
public class ProjectTeamApiController {
	private ProjectTeamService projectTeamService;

	@GetMapping("")
	public List<ProjectTeam> getAllProjectsTeams() {
		return projectTeamService.getAllprojectsTeams();
	}

	@Autowired
	public void setProjectTeamService(ProjectTeamService projectTeamService) {
		this.projectTeamService = projectTeamService;
	}
}