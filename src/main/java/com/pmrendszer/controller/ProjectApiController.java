package com.pmrendszer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {
	private ProjectService projectService;

	@RequestMapping("")
	public List<Project> getAllProjects() {
		return projectService.getAllProjects();
	}
	
	@RequestMapping("/active")
	public List<Project> getActiveProjects() {
		return projectService.getActiveProjects();
	}
	
	@RequestMapping("/search/{name}")
	public ResponseEntity<List<Project>> getProjectsByName(@PathVariable("name") String name) {
		List<Project> result = projectService.getProjectsByName(name);
		
		return getResponseEntity(result);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Project>> getProjectsByDetailedSearch(
			@RequestParam(value = "customerId", defaultValue = "-1") int customerId, 
			@RequestParam(value = "developmentAreaId", defaultValue = "-1") int developmentAreaId, 
			@RequestParam(value = "orderDateMin", defaultValue = "-1") String orderDateMin, 
			@RequestParam(value = "orderDateMax", defaultValue = "-1") String orderDateMax, 
			@RequestParam(value = "projectStatusId", defaultValue = "-1") int projectStatusId, 
			@RequestParam(value = "priorityId", defaultValue = "-1") int priorityId, 
			@RequestParam(value = "projectLeaderId", defaultValue = "-1") int projectLeaderId, 
			@RequestParam(value = "statusId", defaultValue = "-1") int statusId) {
		List<Project> result = projectService.getProcjetsByDetailedSearch(customerId, developmentAreaId, orderDateMin, 
				orderDateMax, projectStatusId, priorityId, projectLeaderId, statusId);
		
		return getResponseEntity(result);
	}
	
	public ResponseEntity<List<Project>> getResponseEntity(List<Project> result){
		ResponseEntity<List<Project>> response;
		if(result.isEmpty()) {
			response = ResponseEntity.notFound().build();
		}else {
			response = ResponseEntity.ok().body(result);
		}
		return response;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}