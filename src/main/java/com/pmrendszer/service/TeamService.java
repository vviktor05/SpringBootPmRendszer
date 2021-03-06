package com.pmrendszer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Team;
import com.pmrendszer.repository.TeamRepo;

@Service
public class TeamService {
	private TeamRepo teamRepo;

	public List<Team> getAllTeams() {
		return teamRepo.findAll();
	}

	public Team getTeamById(int id) throws EntityNotFoundException {
		Team team = teamRepo.findById(id);
		CheckerClass.ifEmptyThrowException(team);

		return team;
	}

	public List<Team> getTeamsWorkingOnProject(int id) throws EntityNotFoundException {
		List<Team> teams = teamRepo.findTeamsWorkingOnProject(id);
//		CheckerClass.ifEmptyThrowException(teams);

		return teams;
	}

	public List<Team> getTeamsNotWorkingOnProject(int id) throws EntityNotFoundException {
		List<Team> teams = teamRepo.findTeamsNotWorkingOnProject(id);
//		CheckerClass.ifEmptyThrowException(teams);

		return teams;
	}

	public Team addTeam(Team team) {
		return teamRepo.save(team);
	}

	public Team updateTeam(int id, Team teamDetails) throws EntityNotFoundException {
		Team team = teamRepo.findById(id);
		CheckerClass.ifEmptyThrowException(team);

		return teamRepo.save(updateTeamDetails(team, teamDetails));
	}

	public void deleteTeam(int id) throws EntityNotFoundException {
		Team team = teamRepo.findById(id);
		CheckerClass.ifEmptyThrowException(team);

		teamRepo.delete(team);
	}

	public void updateTeamsWorkingOnProject(int projectId, List<Team> teams) {
		teamRepo.deleteTeamsWorkingOnProject(projectId);
		
		Team team;
		for (int i = 0; i < teams.size(); i++) {
			team = teams.get(i);
			team.setUpdateMode(true);
			teamRepo.saveTeamsWorkingOnProject(team.getId(), projectId);
		}
	}

	private Team updateTeamDetails(Team team, Team teamDetails) {
		team.setName(teamDetails.getName());
		team.setTeamLeader(teamDetails.getTeamLeader());
		team.setUpdateMode(true);
		return team;
	}

	@Autowired
	public void setTeamRepo(TeamRepo teamRepo) {
		this.teamRepo = teamRepo;
	}
}