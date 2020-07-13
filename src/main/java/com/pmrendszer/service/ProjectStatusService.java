package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.ProjectStatus;
import com.pmrendszer.repository.ProjectStatusRepo;

@Service
public class ProjectStatusService {
	private ProjectStatusRepo projectStatusRepo;

	public List<ProjectStatus> getAllProjectStatuses(){
		return projectStatusRepo.findAll();
	}
	
	@Autowired
	public void setProjectStatusRepo(ProjectStatusRepo projectStatusRepo) {
		this.projectStatusRepo = projectStatusRepo;
	}
}