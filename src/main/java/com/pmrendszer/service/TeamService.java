package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Team;
import com.pmrendszer.repository.TeamRepo;

@Service
public class TeamService {
	private TeamRepo teamRepo;
	
	public List<Team> getAllTeams() {
		return teamRepo.findAll();
	}
	
	public Team getTeamById(int id) {
		return teamRepo.findById(id);
	}
	
	public List<Team> getTeamsWorkingOnProject(int id) {
		return teamRepo.findTeamsWorkingOnProject(id);
	}
	
	public List<Team> getTeamsNotWorkingOnProject(int id) {
		return teamRepo.findTeamsNotWorkingOnProject(id);
	}

	@Autowired
	public void setTeamRepo(TeamRepo teamRepo) {
		this.teamRepo = teamRepo;
	}
}