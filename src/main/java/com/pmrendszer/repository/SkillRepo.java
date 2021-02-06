package com.pmrendszer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmrendszer.domain.Skill;

@Repository
public interface SkillRepo extends CrudRepository<Skill, Integer> {
	List<Skill> findAll();
}