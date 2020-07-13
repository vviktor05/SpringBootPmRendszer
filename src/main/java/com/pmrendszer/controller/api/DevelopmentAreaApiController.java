package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.DevelopmentArea;
import com.pmrendszer.service.DevelopmentAreaService;

@RestController
@RequestMapping("/api")
public class DevelopmentAreaApiController {
	private DevelopmentAreaService developmentAreaService;

	@GetMapping("/project_manager/development_areas")
	public List<DevelopmentArea> getAllDevelopmentAreas(){
		return developmentAreaService.getAllDevelopmentAreas();
	}
	
	@Autowired
	public void setDevelopmentAreaService(DevelopmentAreaService developmentAreaService) {
		this.developmentAreaService = developmentAreaService;
	}
}