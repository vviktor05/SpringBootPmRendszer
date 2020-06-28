package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Report;
import com.pmrendszer.repository.ReportRepo;

@Service
public class ReportService {
	private ReportRepo reportRepo;

	public List<Report> getAllReports() {
		return reportRepo.findAll();
	}

	public Report getReportById(int id) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		return report;
	}

	public void addReport(Report report) {
		reportRepo.save(report);
	}

	public void updateReport(int id, Report reportDetails) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		report.setProject(reportDetails.getProject());
		report.setRecordingDate(reportDetails.getRecordingDate());
		report.setEmployee(reportDetails.getEmployee());
		report.setText(reportDetails.getText());

		reportRepo.save(report);
	}

	public void deleteReport(int id) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		reportRepo.delete(report);
	}

	@Autowired
	public void setReportRepo(ReportRepo reportRepo) {
		this.reportRepo = reportRepo;
	}
}