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
	
	public Project getProjectById(int id) {
		return projectRepo.findById(id);
	}
	
	public List<Project> getProjectsByName(String name){
		return projectRepo.findByNameContainingOrderByName(name);
	}
	
	public List<Project> getProcjetsByDetailedSearch(int customerId, int developmentAreaId, String orderDateMin, String orderDateMax, 
			int projectStatusId, int priorityId, int projectLeaderId, int statusId){
		return projectRepo.detailedSearch(customerId, developmentAreaId, orderDateMin, 
				orderDateMax, projectStatusId, priorityId, projectLeaderId, statusId);
	}
	
	@Autowired
	public void setProjectRepo(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}
}