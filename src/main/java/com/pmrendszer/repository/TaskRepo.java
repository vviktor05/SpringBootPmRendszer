package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
	List<Task> findAll();
	List<Task> findByStatusId(int id);
	Task findById(int id);
	List<Task> findByTopicContainingOrderByTopic(String topic);

	@Query(value = "SELECT * FROM tasks WHERE project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND t.team_leader_id = ?1)", nativeQuery = true)
	List<Task> findMyTeamLeaderTasks(int id);

	@Query(value = "SELECT * FROM tasks WHERE status_id = ?1 AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND t.team_leader_id = ?2)", nativeQuery = true)
	List<Task> findMyTeamLeaderActiveTasks(int statusId, int teamLeaderId);

	@Query(value = "SELECT * FROM tasks WHERE id = ?1 AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t "
			+ "WHERE pt.team_id = t.id AND t.team_leader_id = ?2)", nativeQuery = true)
	Task findTeamLeaderMyTaskById(int taskId, int teamLeaderId);

	@Query(value = "SELECT * FROM tasks WHERE topic like %?1% AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t "
			+ "WHERE pt.team_id = t.id AND t.team_leader_id = ?2) ORDER BY topic", nativeQuery = true)
	List<Task> findMyTeamLeaderTasksByTopic(String topic, int teamLeaderId);

	@Query(value = "SELECT * FROM tasks WHERE project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND  t.id IN "
				+ "(SELECT team_id FROM team_memberships WHERE employee_id = ?1))", nativeQuery = true)
	List<Task> findMyDeveloperTasks(int id);

	@Query(value = "SELECT * FROM tasks WHERE status_id = ?1 AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND  t.id IN "
				+ "(SELECT team_id FROM team_memberships WHERE employee_id = ?2))", nativeQuery = true)
	List<Task> findMyDeveloperActiveTasks(int statusId, int developerId);
	
	@Query(value = "SELECT * FROM tasks WHERE id = ?1 AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND  t.id IN "
				+ "(SELECT team_id FROM team_memberships WHERE employee_id = ?2))", nativeQuery = true)
	Task findMyDeveloperTaskById(int taskId, int developerId);
	
	@Query(value = "SELECT * FROM tasks WHERE topic like %?1%  AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t WHERE pt.team_id = t.id "
			+ "AND  t.id IN "
				+ "(SELECT team_id FROM team_memberships WHERE employee_id = ?2)) ORDER BY topic", nativeQuery = true)
	List<Task> findMyDeveloperTasksByTopic(String topic, int developerId);
}