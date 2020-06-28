package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
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

	@GetMapping("/team_id/{teamId}/employee_id/{employeeId}")
	public TeamMembership getTeamMembershipById(@PathVariable("teamId") int teamId,
			@PathVariable("employeeId") int employeeId) throws EntityNotFoundException {

		return teamMembershipService.getTeamMembershipById(teamId, employeeId);
	}

	@PostMapping("")
	public void addTeamMembership(@Valid @RequestBody TeamMembership teamMembership) {
		teamMembershipService.addTeamMembership(teamMembership);
	}

	@PutMapping("/team_id/{teamId}/employee_id/{employeeId}")
	public void updateTeamMembership(@PathVariable("teamId") int teamId, @PathVariable("employeeId") int employeeId,
			@Valid @RequestBody TeamMembership teamMembership) throws EntityNotFoundException {

		teamMembershipService.updateTeamMembership(teamId, employeeId, teamMembership);
	}

	@DeleteMapping("/team_id/{teamId}/employee_id/{employeeId}")
	public void deleteCustomer(@PathVariable("teamId") int teamId, @PathVariable("employeeId") int employeeId)
			throws EntityNotFoundException {

		teamMembershipService.deleteTeamMembership(teamId, employeeId);
	}

	@Autowired
	public void setTeamMembershipService(TeamMembershipService teamMembershipService) {
		this.teamMembershipService = teamMembershipService;
	}
}