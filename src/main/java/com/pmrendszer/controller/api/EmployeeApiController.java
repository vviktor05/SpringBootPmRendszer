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
import com.pmrendszer.domain.Employee;
import com.pmrendszer.service.EmployeeService;

@RestController
@Validated
@RequestMapping("/api")
public class EmployeeApiController {
	private EmployeeService employeeService;

	@GetMapping("/project_manager/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/project_manager/employees/id/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/project_manager/employees/in_team/team_id/{id}")
	public List<Employee> getTeamMembers(
			@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return employeeService.getTeamMembers(id);
	}

	@GetMapping("/project_manager/employees/not_in_team/team_id/{id}")
	public List<Employee> getNotTeamMembers(
			@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {

		return employeeService.getNotTeamMembers(id);
	}

	@PostMapping("/project_manager/employees")
	public void addEmployee(@Valid @RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@PutMapping("/project_manager/employees/{id}")
	public void updateEmployee(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Employee employeeDetails) throws EntityNotFoundException {

		employeeService.updateEmployee(id, employeeDetails);
	}

	@DeleteMapping("/project_manager/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		
		employeeService.deleteEmployee(id);
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}