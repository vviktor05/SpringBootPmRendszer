package com.pmrendszer.controller.api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import com.pmrendszer.domain.Team;
import com.pmrendszer.service.TeamService;

@RestController
@Validated
@RequestMapping("/api/teams")
public class TeamApiController {
	private TeamService teamService;

	@GetMapping("")
	public List<Team> getAllTeams(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return teamService.getAllTeams();
		}else {
			
		}
		return null;
	}

	@GetMapping("/id/{id}")
	public Team getTeamById(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return teamService.getTeamById(id);
	}

	@GetMapping("/working_on/project_id/{id}")
	public List<Team> getTeamsWorkingOnProject(
			@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return teamService.getTeamsWorkingOnProject(id);
	}

	@GetMapping("/not_working_on/project_id/{id}")
	public List<Team> getTeamsNotWorkingOnProject(
			@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return teamService.getTeamsNotWorkingOnProject(id);
	}

	@PostMapping("")
	public void addTeam(@Valid @RequestBody Team team) {
		teamService.addTeam(team);
	}

	@PutMapping("/{id}")
	public void updateTeam(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Team teamDetails) throws EntityNotFoundException {

		teamService.updateTeam(id, teamDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		teamService.deleteTeam(id);
	}

	@Autowired
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
}