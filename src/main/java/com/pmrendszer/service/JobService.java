package com.pmrendszer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmrendszer.domain.Job;
import com.pmrendszer.repository.JobRepo;

@Service
public class JobService {
	private JobRepo jobRepo;

	public List<Job> getAllJobs(){
		return jobRepo.findAll();
	}
	
	@Autowired
	public void setJobRepo(JobRepo jobRepo) {
		this.jobRepo = jobRepo;
	}
}