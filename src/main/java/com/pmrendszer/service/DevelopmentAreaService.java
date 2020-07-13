package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.DevelopmentArea;
import com.pmrendszer.repository.DevelopmentAreaRepo;

@Service
public class DevelopmentAreaService {
	private DevelopmentAreaRepo developmentAreaRepo;

	public List<DevelopmentArea> getAllDevelopmentAreas(){
		return developmentAreaRepo.findAll();
	}
	
	@Autowired
	public void setDevelopmentAreaRepo(DevelopmentAreaRepo developmentAreaRepo) {
		this.developmentAreaRepo = developmentAreaRepo;
	}
}