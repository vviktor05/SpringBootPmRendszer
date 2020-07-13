package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Priority;
import com.pmrendszer.repository.PriorityRepo;

@Service
public class PriorityService {
	private PriorityRepo priorityRepo;

	public List<Priority> getAllPriorities(){
		return priorityRepo.findAll();
	}
	
	@Autowired
	public void setPriorityRepo(PriorityRepo priorityRepo) {
		this.priorityRepo = priorityRepo;
	}
}