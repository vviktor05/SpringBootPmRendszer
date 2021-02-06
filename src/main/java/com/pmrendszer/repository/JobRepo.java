package com.pmrendszer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pmrendszer.domain.Job;

@Repository
public interface JobRepo extends CrudRepository<Job, Integer> {
	List<Job> findAll();
}