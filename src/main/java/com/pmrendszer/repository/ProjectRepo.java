package com.pmrendszer.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Project;

@Repository
public interface ProjectRepo extends CrudRepository<Project, Integer> {
	List<Project> findAll();
	List<Project> findByStatusId(int id);
	Project findById(int id);
	List<Project> findByNameContainingOrderByName(String name);

	@Query(value = "SELECT * FROM projects WHERE "
			+ "(?1 = -1 OR customer_id = ?1) AND (?2 = -1 OR development_area_id = ?2) "
			+ "AND order_date >= ?3 AND order_date <= ?4 "
			+ "AND (?5 = -1 OR project_status_id = ?5) AND (?6 = -1 OR priority_id = ?6) "
			+ "AND (?7 = -1 OR project_leader_id = ?7) AND (?8 = -1 OR status_id = ?8)", nativeQuery = true)
	List<Project> detailedSearch(int customerId, int developmentAreaId, Date orderDateMin, Date orderDateMax,
			int projectStatusId, int priorityId, int projectLeaderId, int statusId);

	@Query(value = "SELECT * FROM projects WHERE id IN "
			+ "(SELECT project_id FROM projects_teams pt, team_memberships tm "
			+ "WHERE pt.team_id = tm.team_id AND tm.employee_id = ?1)", nativeQuery = true)
	List<Project> findMyProjects(int employeeId);

	@Query(value = "SELECT * FROM projects WHERE id IN "
			+ "(SELECT project_id FROM projects_teams pt, team_memberships tm "
			+ "WHERE pt.team_id = tm.team_id AND tm.employee_id = ?1) AND status_id = 3", nativeQuery = true)
	List<Project> findMyActiveProjects(int employeeId);
	
	@Query(value = "SELECT * FROM projects WHERE id = ?1 AND id IN "
			+ "(SELECT project_id FROM projects_teams pt, team_memberships tm "
			+ "WHERE pt.team_id = tm.team_id AND tm.employee_id = ?2)", nativeQuery = true)
	Project findMyProjectById(int projectId, int employeeId);
	
	@Query(value = "SELECT * FROM projects WHERE name like %:projectName% AND id IN "
			+ "(SELECT project_id FROM projects_teams pt, team_memberships tm "
			+ "WHERE pt.team_id = tm.team_id AND tm.employee_id = :employeeId)", nativeQuery = true)
	List<Project> findMyProjectsByName(@Param("projectName") String projectName, @Param("employeeId") int employeeId);

	@Query(value = "SELECT * FROM projects WHERE "
			+ "(?1 = -1 OR customer_id = ?1) AND (?2 = -1 OR development_area_id = ?2) "
			+ "AND order_date >= ?3 AND order_date <= ?4 "
			+ "AND (?5 = -1 OR project_status_id = ?5) AND (?6 = -1 OR priority_id = ?6) "
			+ "AND (?7 = -1 OR project_leader_id = ?7) AND (?8 = -1 OR status_id = ?8) "
			+ "AND id IN (SELECT project_id FROM projects_teams pt, team_memberships tm "
			+ "WHERE pt.team_id = tm.team_id AND tm.employee_id = ?9)", nativeQuery = true)
	List<Project> findMyProjectsBydetailedSearch(int customerId, int developmentAreaId, Date orderDateMin, Date orderDateMax,
			int projectStatusId, int priorityId, int projectLeaderId, int statusId, int employeeId); 
}