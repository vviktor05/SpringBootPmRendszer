package com.pmrendszer.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.pmrendszer.domain.Employee;
import com.pmrendszer.service.EmployeeService;

@RestController
public class AuthApiController {
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/api/basicauth")
	public String basicAuth() {
		return RequestContextHolder.currentRequestAttributes().getSessionId();
	}
	
	@RequestMapping("/api/logged_employee")
	public Employee getLoggedEmployee() {
		return employeeService.getEmployeeByEmail(employeeService.getAuthenticatedEmployee().getEmail());
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}