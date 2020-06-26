package com.pmrendszer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
}