package com.pmrendszer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmrendszer.domain.Team;

@Repository
public interface TeamRepo extends CrudRepository<Team, Integer> {
	List<Team> findAll();

	Team findById(int id);

	@Query(value = "SELECT * FROM teams WHERE id IN (SELECT team_id FROM projects_teams WHERE project_id = ?1)", nativeQuery = true)
	List<Team> findTeamsWorkingOnProject(int id);

	@Query(value = "SELECT * FROM teams WHERE id NOT IN (SELECT team_id FROM projects_teams WHERE project_id = ?1)", nativeQuery = true)
	List<Team> findTeamsNotWorkingOnProject(int id);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM projects_teams WHERE project_id = ?1", nativeQuery = true)
	void deleteTeamsWorkingOnProject(int projectId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO projects_teams VALUES (?1, ?2)", nativeQuery = true)
	void saveTeamsWorkingOnProject(int teamId, int projectId);
}