package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
@RequestMapping("/api")
public class TeamMembershipApiController implements Roles{
	private TeamMembershipService teamMembershipService;

	@GetMapping("/project_manager/team_memberships")
	public List<TeamMembership> getAllTeamMemberships() {
		return teamMembershipService.getAllTeamMemberships();
	}

	@GetMapping("/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}")
	public TeamMembership getTeamMembershipById(
			@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId,
			@PathVariable("employeeId") int employeeId) throws EntityNotFoundException {

		return teamMembershipService.getTeamMembershipById(teamId, employeeId);
	}

	@PostMapping("/project_manager/team_memberships")
	public TeamMembership addTeamMembership(@Valid @RequestBody TeamMembership teamMembership) {
		return teamMembershipService.addTeamMembership(teamMembership);
	}

	@PutMapping("/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}")
	public TeamMembership updateTeamMembership(@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId,
			@PathVariable("employeeId") @Min(value = 1, message = "{id.path.valid}") int employeeId,
			@Valid @RequestBody TeamMembership teamMembership) throws EntityNotFoundException {

		return teamMembershipService.updateTeamMembership(teamId, employeeId, teamMembership);
	}

	@DeleteMapping("/project_manager/team_memberships/team_id/{teamId}/employee_id/{employeeId}")
	public void deleteCustomer(
			@PathVariable("teamId") @Min(value = 1, message = "{id.path.valid}") int teamId,
			@PathVariable("employeeId") @Min(value = 1, message = "{id.path.valid}") int employeeId)
			throws EntityNotFoundException {

		teamMembershipService.deleteTeamMembership(teamId, employeeId);
	}

	@Autowired
	public void setTeamMembershipService(TeamMembershipService teamMembershipService) {
		this.teamMembershipService = teamMembershipService;
	}
}