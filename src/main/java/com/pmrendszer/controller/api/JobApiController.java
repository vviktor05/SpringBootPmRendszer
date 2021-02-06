package com.pmrendszer.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmrendszer.domain.Job;
import com.pmrendszer.service.JobService;

@RestController
@RequestMapping("/api")
public class JobApiController {
	private JobService jobService;

	@GetMapping("/project_manager/jobs")
	public List<Job> getAllJobs() {
		return jobService.getAllJobs();
	}

	@Autowired
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
}