package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Project;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {
	List<Project> findAll();
	List<Project> findByStatusId(int id);
}