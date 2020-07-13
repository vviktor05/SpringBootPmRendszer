package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Status;
import com.pmrendszer.service.StatusService;

@RestController
@RequestMapping("/api")
public class StatusApiController {
	private StatusService statusService;

	@GetMapping("/project_manager/statuses")
	public List<Status> getAllStatuses(){
		return statusService.getAllStatuses();
	}
	
	@Autowired
	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
}