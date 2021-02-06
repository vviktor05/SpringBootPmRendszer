package com.pmrendszer.controller.api;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
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
import com.pmrendszer.domain.Employee;
import com.pmrendszer.service.EmployeeService;

@RestController
//@Validated
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
	
	@GetMapping("/project_manager/employees/email/{email}")
	public Employee getEmployeeByEmail(@PathVariable(value = "email") String email)
			throws EntityNotFoundException {

		return employeeService.getEmployeeByEmail(email);
	}

	@GetMapping("/project_manager/employees/team_leaders")
	public List<Employee> getAllTeamLeaderEmployees() {
		return employeeService.getAllTeamLeaderEmployees();
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
	public Employee addEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@PutMapping("/project_manager/employees/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id,
			@Valid @RequestBody Employee employeeDetails) throws EntityNotFoundException {

		return employeeService.updateEmployee(id, employeeDetails);
	}

	@DeleteMapping("/project_manager/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int id)
			throws EntityNotFoundException {
		
		employeeService.deleteEmployee(id);
	}
	
	@PutMapping({"/project_manager/employees/team_membership/team_id/{id}", "/team_leader/employees/team_membership/team_id/{id}"})
	public void updateTeamMemberships(
			@PathVariable(value = "id") @Min(value = 1, message = "{id.path.valid}") int teamId,
			@RequestBody List<Employee> employees) throws EntityNotFoundException {

		employeeService.updateTeamMemberships(teamId, employees);
	}

	@GetMapping("/team_leader/employees")
	public List<Employee> getMyEmployees() {
		return employeeService.getMyEmployees();
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}