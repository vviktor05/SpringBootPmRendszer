package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.repository.EmployeeRepo;

@Service
public class EmployeeService {
	private EmployeeRepo employeeRepo;
	
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
	public List<Employee> getTeamMembers(int id) {
		return employeeRepo.findTeamMembers(id);
	}
	
	public List<Employee> getNotTeamMembers(int id) {
		return employeeRepo.findNotTeamMembers(id);
	}
	
	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
}