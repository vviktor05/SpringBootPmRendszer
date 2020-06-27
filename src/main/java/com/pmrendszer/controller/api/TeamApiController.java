package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Team;
import com.pmrendszer.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamApiController {
	private TeamService teamService;

	@RequestMapping("")
	public List<Team> getAllTeams(){
		return teamService.getAllTeams();
	}
	
	@RequestMapping("/id/{id}")
	public Team getTeamById(@PathVariable(value = "id") int id) {
		return teamService.getTeamById(id);
	}
	
	@RequestMapping("/working_on/project_id/{id}")
	public List<Team> getTeamsWorkingOnProject(@PathVariable(value = "id") int id){
		return teamService.getTeamsWorkingOnProject(id);
	}
	
	@RequestMapping("/not_working_on/project_id/{id}")
	public List<Team> getTeamsNotWorkingOnProject(@PathVariable(value = "id") int id){
		return teamService.getTeamsNotWorkingOnProject(id);
	}
	
	@Autowired
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
}