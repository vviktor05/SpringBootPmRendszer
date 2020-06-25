package com.pmrendszer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}