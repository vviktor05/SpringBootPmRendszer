package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.ProjectTeam;

@Repository
public interface ProjectTeamRepo extends CrudRepository<ProjectTeam, Integer> {
	List<ProjectTeam> findAll();
	ProjectTeam findByProjectIdAndTeamId(int projectId, int teamId);
}