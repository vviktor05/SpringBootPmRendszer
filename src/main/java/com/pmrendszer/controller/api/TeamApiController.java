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
import com.pmrendszer.domain.Team;
import com.pmrendszer.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamApiController {
	private TeamService teamService;

	@GetMapping("")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping("/id/{id}")
	public Team getTeamById(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		return teamService.getTeamById(id);
	}

	@PostMapping("")
	public void addTeam(@Valid @RequestBody Team team) {
		teamService.addTeam(team);
	}

	@PutMapping("/{id}")
	public void updateTeam(@PathVariable(value = "id") int id, @Valid @RequestBody Team teamDetails)
			throws EntityNotFoundException {
		teamService.updateTeam(id, teamDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		teamService.deleteTeam(id);
	}

	@Autowired
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
}