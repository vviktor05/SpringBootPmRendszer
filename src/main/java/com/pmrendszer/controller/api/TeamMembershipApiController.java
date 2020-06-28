package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.TeamMembership;
import com.pmrendszer.service.TeamMembershipService;

@RestController
@RequestMapping("api/team_memberships")
public class TeamMembershipApiController {
	private TeamMembershipService teamMembershipService;

	@GetMapping("")
	public List<TeamMembership> getAllTeamMemberships() {
		return teamMembershipService.getAllTeamMemberships();
	}

	@Autowired
	public void setTeamMembershipService(TeamMembershipService teamMembershipService) {
		this.teamMembershipService = teamMembershipService;
	}
}