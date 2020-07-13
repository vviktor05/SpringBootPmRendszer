package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.Roles;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.domain.Project;
import com.pmrendszer.domain.Report;
import com.pmrendszer.repository.ReportRepo;

@Service
public class ReportService implements Roles {
	private ReportRepo reportRepo;
	private ProjectService projectService;
	private EmployeeService employeeService;

	public List<Report> getAllReports() {
		List<Report> reports = reportRepo.findAll();
		CheckerClass.ifEmptyThrowException(reports);

		return reports;
	}

	public Report getReportById(int id) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		return report;
	}

	public Report addReport(Report report) {
		return reportRepo.save(report);
	}

	public Report updateReport(int id, Report reportDetails) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		return reportRepo.save(updateReportDetails(report, reportDetails));
	}

	public void deleteReport(int id) throws EntityNotFoundException {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		reportRepo.delete(report);
	}
	
	public List<Report> getMyReports() {
		return reportRepo.findMyReports(getAuthenticatedEmployee().getId());
	}

	public Report getMyReportById(int id) throws EntityNotFoundException {
		Report report = reportRepo.findMyReportById(id, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(report);

		return report;
	}

	public Report addMyReport(Report report) throws Exception {
		List<Project> myProjects = projectService.getMyProjects();

		if (myProjects.contains(report.getProject())) {
			report = reportRepo.save(report);
		} else {
			throw new Exception("Csak a saját projektekhez lehet hozzáadni jelentést!");
		}

		return report;
	}

	public Report updateMyReport(int id, Report reportDetails) throws EntityNotFoundException, Exception {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		List<Report> myReports = getMyReports();

		if (myReports.contains(report)) {
			report = reportRepo.save(updateReportDetails(report, reportDetails));
		} else {
			throw new Exception("Csak a saját jelentéseket lehet szerkeszteni!");
		}

		return report;
	}

	public void deleteMyReport(int id) throws EntityNotFoundException, Exception {
		Report report = reportRepo.findById(id);
		CheckerClass.ifEmptyThrowException(report);

		List<Report> myReports = getMyReports();

		if (myReports.contains(report)) {
			reportRepo.delete(report);
		} else {
			throw new Exception("Csak a saját jelentéseket lehet törölni!");
		}
	}

	private Employee getAuthenticatedEmployee() {
		return employeeService.getAuthenticatedEmployee();
	}

	private Report updateReportDetails(Report report, Report reportDetails) {
		report.setProject(reportDetails.getProject());
		report.setRecordingDate(reportDetails.getRecordingDate());
		report.setEmployee(reportDetails.getEmployee());
		report.setText(reportDetails.getText());
		report.setUpdateMode(true);
		return report;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	public void setReportRepo(ReportRepo reportRepo) {
		this.reportRepo = reportRepo;
	}
}