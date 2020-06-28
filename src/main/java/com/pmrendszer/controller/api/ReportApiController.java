package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Report;
import com.pmrendszer.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportApiController {
	private ReportService reportService;

	@GetMapping("")
	public List<Report> getAllReports() {
		return reportService.getAllReports();
	}

	@GetMapping("/id/{id}")
	public Report getReportById(@PathVariable("id") int id) throws EntityNotFoundException {
		return reportService.getReportById(id);
	}

	@PostMapping("")
	public void addReport(@Valid @RequestBody Report report) {
		reportService.addReport(report);
	}

	@PutMapping("/{id}")
	public void updateReport(@PathVariable(value = "id") int id, @Valid @RequestBody Report reportDetails)
			throws EntityNotFoundException {
		reportService.updateReport(id, reportDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteReport(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		reportService.deleteReport(id);
	}

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}