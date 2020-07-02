package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RequestMapping("/api")
public class ReportApiController implements Roles {
	private ReportService reportService;

	@GetMapping("/project_manager/reports")
	public List<Report> getAllReports() {
		return reportService.getAllReports();
	}

	@GetMapping("/project_manager/reports/id/{id}")
	public Report getReportById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return reportService.getReportById(id);
	}

	@PostMapping("/project_manager/reports")
	public Report addReport(@Valid @RequestBody Report report)  {
		return reportService.addReport(report);
	}

	@PutMapping("/project_manager/reports/{id}")
	public Report updateReport(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Report reportDetails) throws EntityNotFoundException {

		return reportService.updateReport(id, reportDetails);
	}

	@DeleteMapping("/project_manager/reports/{id}")
	public void deleteReport(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		reportService.deleteReport(id);
	}

	@GetMapping("/team_leader/reports")
	public List<Report> getMyReports() {
		return reportService.getMyReports();
	}

	@GetMapping("/team_leader/reports/id/{id}")
	public Report getMyReportById(@PathVariable("id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return reportService.getMyReportById(id);
	}

	@PostMapping("/team_leader/reports")
	public Report addMyReport(@Valid @RequestBody Report report) throws Exception {
		return reportService.addMyReport(report);
	}

	@PutMapping("/team_leader/reports/{id}")
	public Report updateMyReport(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Report reportDetails) throws EntityNotFoundException, Exception {

		return reportService.updateMyReport(id, reportDetails);
	}

	@DeleteMapping("/team_leader/reports/{id}")
	public void deleteMyReport(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException, Exception {

		reportService.deleteMyReport(id);
	}

	@Autowired
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}