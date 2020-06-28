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
import com.pmrendszer.domain.Employee;
import com.pmrendszer.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
public class EmployeeApiController {
	private EmployeeService employeeService;

	@GetMapping("")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("")
	public void addEmployee(@Valid @RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@PutMapping("/{id}")
	public void updateEmployee(@PathVariable(value = "id") int id, @Valid @RequestBody Employee employeeDetails)
			throws EntityNotFoundException {
		employeeService.updateEmployee(id, employeeDetails);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(value = "id") int id) throws EntityNotFoundException {
		employeeService.deleteEmployee(id);
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}