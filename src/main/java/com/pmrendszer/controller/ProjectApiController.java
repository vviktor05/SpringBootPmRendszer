package com.pmrendszer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.ProjectService;

@RestController
@RequestMapping("/projects")
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
	
	@RequestMapping("/{name}")
	public ResponseEntity<List<Project>> getProjectsByName(@PathVariable("name") String name) {
		ResponseEntity<List<Project>> response;
		List<Project> result = projectService.getProjectsByName(name);
		
		if(result.isEmpty()) {
			response = new ResponseEntity<List<Project>>(result, HttpStatus.NOT_FOUND);
		}else {
			response = new ResponseEntity<List<Project>>(result, HttpStatus.OK);
		}
		return response;
	}
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}