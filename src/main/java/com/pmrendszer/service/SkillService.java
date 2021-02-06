package com.pmrendszer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pmrendszer.domain.Skill;
import com.pmrendszer.repository.SkillRepo;

@Service
public class SkillService {
	private SkillRepo skillRepo;

	public List<Skill> getAllSkills() {
		return skillRepo.findAll();
	}

	@Autowired
	public void setSkillRepo(SkillRepo skillRepo) {
		this.skillRepo = skillRepo;
	}
}