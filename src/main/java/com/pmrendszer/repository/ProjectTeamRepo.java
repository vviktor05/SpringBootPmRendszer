package com.pmrendszer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmrendszer.domain.ProjectTeam;

@Repository
public interface ProjectTeamRepo extends CrudRepository<ProjectTeam, Integer> {
	List<ProjectTeam> findAll();
	ProjectTeam findByProjectIdAndTeamId(int projectId, int teamId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM projects_teams WHERE project_id = ?1", nativeQuery = true)
	void deleteByProjectId(int projectId);
}