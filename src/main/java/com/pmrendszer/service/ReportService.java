package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Report;
import com.pmrendszer.repository.ReportRepo;

@Service
public class ReportService {
	private ReportRepo reportRepo;

	public List<Report> getAllReports(){
		return reportRepo.findAll();
	}
	
	public Report getReportById(int id) {
		return reportRepo.findById(id);
	}
	
	@Autowired
	public void setReportRepo(ReportRepo reportRepo) {
		this.reportRepo = reportRepo;
	}
}