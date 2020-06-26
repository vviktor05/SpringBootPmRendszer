package com.pmrendszer.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.service.EmployeeService;

@RestController
@RequestMapping("api/employees")
public class EmployeeApiController {
	private EmployeeService employeeService;
	
	@RequestMapping("")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/search/in_team/team_id/{id}")
	public List<Employee> getTeamMembers(@PathVariable(value = "id") int id){
		return employeeService.getTeamMembers(id);
	}
	
	@RequestMapping("/search/not_in_team/team_id/{id}")
	public List<Employee> getNotTeamMembers(@PathVariable(value = "id") int id){
		return employeeService.getNotTeamMembers(id);
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}