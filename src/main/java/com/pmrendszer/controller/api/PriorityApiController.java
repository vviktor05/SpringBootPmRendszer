package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Priority;
import com.pmrendszer.service.PriorityService;

@RestController
@RequestMapping("/api")
public class PriorityApiController {
	private PriorityService priorityService;

	@GetMapping("/project_manager/priorities")
	public List<Priority> getAllPriorities(){
		return priorityService.getAllPriorities();
	}
	
	@Autowired
	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}
}