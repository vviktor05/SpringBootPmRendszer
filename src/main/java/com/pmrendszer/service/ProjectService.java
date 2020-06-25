package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Project;
import com.pmrendszer.repository.ProjectRepo;

@Service
public class ProjectService {
	private ProjectRepo projectRepo;
	private final int ACTIVE_STATUS_ID = 3;

	public List<Project> getAllProjects(){
		return projectRepo.findAll();
	}
	
	public List<Project> getActiveProjects(){
		return projectRepo.findByStatusId(ACTIVE_STATUS_ID);
	}
	
	public List<Project> getProjectsByName(String name){
		return projectRepo.findByNameContainingOrderByName(name);
	}
	
	@Autowired
	public void setProjectRepo(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}
}