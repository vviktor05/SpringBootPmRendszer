package com.pmrendszer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pmrendszer.domain.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
	final int JOB_DEVELOPER_ID = 3;
	final int JOB_TEAM_LEADER_ID = 2;

	List<Employee> findAll();

	Employee findById(int id);
	
	@Query(value = "SELECT count(*) FROM employees", nativeQuery = true)
	int countEmployees();

	@Query(value = "SELECT * FROM employees WHERE job_id=" + JOB_TEAM_LEADER_ID, nativeQuery = true)
	List<Employee> findAllTeamLeader();

	@Query(value = "SELECT * FROM employees WHERE job_id=" + JOB_DEVELOPER_ID + " AND id IN "
			+ "(SELECT employee_id FROM team_memberships " + "WHERE team_id = ?1)", nativeQuery = true)
	List<Employee> findTeamMembers(int id);

	@Query(value = "SELECT * FROM employees WHERE job_id=" + JOB_DEVELOPER_ID + " AND id NOT IN "
			+ "(SELECT employee_id FROM team_memberships " + "WHERE team_id = ?1)", nativeQuery = true)
	List<Employee> findNotTeamMembers(int id);

	Employee findByEmail(String email);

	@Query(value = "SELECT * FROM employees WHERE id IN " + "(SELECT employee_id FROM team_memberships tm, teams t "
			+ "WHERE tm.team_id = t.id AND t.team_leader_id = ?1)", nativeQuery = true)
	List<Employee> findMyEmployees(int teamLeaderId);

	@Query(value = "SELECT * FROM employees WHERE id = ?1 AND id IN "
			+ "(SELECT employee_id FROM team_memberships tm, teams t "
			+ "WHERE tm.team_id = t.id AND t.team_leader_id = ?2)", nativeQuery = true)
	Employee findMyEmployeeById(int employeeId, int teamLeaderId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM team_memberships WHERE team_id = ?1", nativeQuery = true)
	void deleteTeamMemberships(int teamId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO team_memberships VALUES (?1, ?2)", nativeQuery = true)
	void saveTeamMemberships(int employeeId, int teamId);
	
	Boolean existsByEmail(String email);
}