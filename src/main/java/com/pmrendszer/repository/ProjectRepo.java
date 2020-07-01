package com.pmrendszer.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
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
}