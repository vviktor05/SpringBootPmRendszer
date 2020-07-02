package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Report;

@Repository
public interface ReportRepo extends CrudRepository<Report, Integer> {
	List<Report> findAll();
	Report findById(int id);
	
	@Query(value = "SELECT * FROM reports WHERE project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t "
			+ "WHERE pt.team_id = t.id AND t.team_leader_id = ?1)", nativeQuery = true)
	List<Report> findMyReports(int id);
	
	@Query(value = "SELECT * FROM reports WHERE id = ?1 AND project_id IN "
			+ "(SELECT project_id FROM projects_teams pt, teams t "
			+ "WHERE pt.team_id = t.id AND t.team_leader_id = ?2)", nativeQuery = true)
	Report findMyReportById(int reportId, int employeeId);
}