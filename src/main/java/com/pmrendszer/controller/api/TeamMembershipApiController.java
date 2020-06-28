package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
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

	@GetMapping("/in_team/team_id/{id}")
	public List<Employee> getTeamMembers(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		return teamMembershipService.getTeamMembers(id);
	}

	@GetMapping("/not_in_team/team_id/{id}")
	public List<Employee> getNotTeamMembers(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		return teamMembershipService.getNotTeamMembers(id);
	}

	@Autowired
	public void setTeamMembershipService(TeamMembershipService teamMembershipService) {
		this.teamMembershipService = teamMembershipService;
	}
}