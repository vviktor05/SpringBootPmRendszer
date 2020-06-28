package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.domain.Team;
import com.pmrendszer.repository.ProjectTeamRepo;

@Service
public class ProjectTeamService {
	private ProjectTeamRepo projectTeamRepo;

	public List<ProjectTeam> getAllprojectsTeams() {
		return projectTeamRepo.findAll();
	}

	public List<Team> getTeamsWorkingOnProject(int id) throws EntityNotFoundException {
		List<Team> teams = projectTeamRepo.findTeamsWorkingOnProject(id);
		CheckerClass.ifEmptyThrowException(teams);

		return teams;
	}

	public List<Team> getTeamsNotWorkingOnProject(int id) throws EntityNotFoundException {
		List<Team> teams = projectTeamRepo.findTeamsNotWorkingOnProject(id);
		CheckerClass.ifEmptyThrowException(teams);

		return teams;
	}

	@Autowired
	public void setProjectTeamRepo(ProjectTeamRepo projectTeamRepo) {
		this.projectTeamRepo = projectTeamRepo;
	}
}