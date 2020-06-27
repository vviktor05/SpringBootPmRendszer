package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {
	final int JOB_DEVELOPER_ID = 3;
	List<Employee> findAll();
	@Query(value = "SELECT * FROM employees WHERE job_id=" + JOB_DEVELOPER_ID + " AND id IN "
					+ "(SELECT employee_id FROM team_memberships "
					+ "WHERE team_id = ?1)", nativeQuery = true)
	List<Employee> findTeamMembers(int id);
	@Query(value = "SELECT * FROM employees WHERE job_id=" + JOB_DEVELOPER_ID + " AND id NOT IN "
					+ "(SELECT employee_id FROM team_memberships "
					+ "WHERE team_id = ?1)", nativeQuery = true)
	List<Employee> findNotTeamMembers(int id);
}