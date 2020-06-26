package com.pmrendszer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Report;
import com.pmrendszer.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportApiController {
	private ReportService reportService;
	
	@RequestMapping("")
	public List<Report> getAllReports() {
		return reportService.getAllReports();
	}
	
	@RequestMapping("/search/id/{id}")
	public Report getReportById(@PathVariable("id") int id) {
		return reportService.getReportById(id);
	}
	
	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}