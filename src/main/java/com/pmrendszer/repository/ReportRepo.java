package com.pmrendszer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pmrendszer.domain.Report;

@Repository
public interface ReportRepo extends CrudRepository<Report, Integer> {
	List<Report> findAll();
	Report findById(int id);
}