package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.ProjectTeam;
import com.pmrendszer.repository.ProjectTeamRepo;

@Service
public class ProjectTeamService {
	private ProjectTeamRepo projectTeamRepo;

	public List<ProjectTeam> getAllprojectsTeams() {
		return projectTeamRepo.findAll();
	}

	public ProjectTeam getProjectTeamById(int projectId, int teamId) throws EntityNotFoundException {
		ProjectTeam projectTeam = projectTeamRepo.findByProjectIdAndTeamId(projectId, teamId);
		CheckerClass.ifEmptyThrowException(projectTeam);

		return projectTeam;
	}

	public ProjectTeam addProjectTeam(ProjectTeam projectTeam) {
		return projectTeamRepo.save(projectTeam);
	}

	public ProjectTeam updateProjectTeam(int projectId, int teamId, ProjectTeam projectTeamDetails)
			throws EntityNotFoundException {

		ProjectTeam projectTeam = projectTeamRepo.findByProjectIdAndTeamId(projectId, teamId);
		CheckerClass.ifEmptyThrowException(projectTeam);

		projectTeam.setProject(projectTeamDetails.getProject());
		projectTeam.setTeam(projectTeamDetails.getTeam());
		return projectTeamRepo.save(projectTeam);
	}

	public void deleteProjectTeam(int projectId, int teamId) throws EntityNotFoundException {
		ProjectTeam projectTeam = projectTeamRepo.findByProjectIdAndTeamId(projectId, teamId);
		CheckerClass.ifEmptyThrowException(projectTeam);

		projectTeamRepo.delete(projectTeam);
	}
	
	public void deleteAllProjectTeamByProjectId(int projectId) throws EntityNotFoundException {
		projectTeamRepo.deleteByProjectId(projectId);
	}

	@Autowired
	public void setProjectTeamRepo(ProjectTeamRepo projectTeamRepo) {
		this.projectTeamRepo = projectTeamRepo;
	}
}