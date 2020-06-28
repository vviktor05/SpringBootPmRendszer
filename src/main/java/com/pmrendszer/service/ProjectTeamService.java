package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.repository.ProjectTeamRepo;

@Service
public class ProjectTeamService {
	private ProjectTeamRepo projectTeamRepo;

	public List<ProjectTeam> getAllprojectsTeams() {
		return projectTeamRepo.findAll();
	}

	@Autowired
	public void setProjectTeamRepo(ProjectTeamRepo projectTeamRepo) {
		this.projectTeamRepo = projectTeamRepo;
	}
}