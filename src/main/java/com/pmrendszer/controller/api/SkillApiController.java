package com.pmrendszer.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pmrendszer.domain.Skill;
import com.pmrendszer.service.SkillService;

@RestController
@RequestMapping("/api")
public class SkillApiController {
	private SkillService skillService;

	@GetMapping("/project_manager/skills")
	public List<Skill> getAllSkills() {
		return skillService.getAllSkills();
	}
	
	@Autowired
	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}
}