package com.pmrendszer.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.DevelopmentArea;

@Repository
public class DevelopmentAreaRepo {
	private JdbcTemplate jdbc;

	public List<DevelopmentArea>findAll() {
		String sql = "SELECT * FROM development_areas";
		return jdbc.query(sql, (rs, i) -> new DevelopmentArea(
				rs.getInt("id"),
				rs.getString("name")				
				));
	}
	
	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
}