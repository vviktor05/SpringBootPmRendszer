package com.pmrendszer.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pmrendszer.domain.ProjectStatus;

@Repository
public class ProjectStatusRepo {
	private JdbcTemplate jdbc;

	public List<ProjectStatus> findAll(){
		String sql = "SELECT * FROM project_statuses";
		return jdbc.query(sql, new RowMapper<ProjectStatus>() {
			@Override
			public ProjectStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectStatus projectStatus = new ProjectStatus();
				projectStatus.setId(rs.getInt("id"));
				projectStatus.setName(rs.getString("name"));
				return projectStatus;
			}
		});
	}
	
	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
}