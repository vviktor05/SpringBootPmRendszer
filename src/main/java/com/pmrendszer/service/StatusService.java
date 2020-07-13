package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Status;
import com.pmrendszer.repository.StatusRepo;

@Service
public class StatusService {
	private StatusRepo statusRepo;

	public List<Status> getAllStatuses(){
		return statusRepo.findAll();
	}
	
	@Autowired
	public void setStatusRepo(StatusRepo statusRepo) {
		this.statusRepo = statusRepo;
	}
}