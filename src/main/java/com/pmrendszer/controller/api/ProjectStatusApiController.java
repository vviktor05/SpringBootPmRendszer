package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.ProjectStatus;
import com.pmrendszer.service.ProjectStatusService;

@RestController
@RequestMapping("/api")
public class ProjectStatusApiController {
	private ProjectStatusService projectStatusService;

	@RequestMapping("/project_manager/project_statuses")
	public List<ProjectStatus> getAllProjectStatuses(){
		return projectStatusService.getAllProjectStatuses();
	}
	
	@Autowired
	public void setProjectStatusService(ProjectStatusService projectStatusService) {
		this.projectStatusService = projectStatusService;
	}
}