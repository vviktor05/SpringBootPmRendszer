package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.repository.EmployeeRepo;

@Service
public class EmployeeService {
	private EmployeeRepo employeeRepo;

	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	public Employee getEmployeeById(int id) throws EntityNotFoundException {
		Employee employee = employeeRepo.findById(id);
		CheckerClass.ifEmptyThrowException(employee);

		return employee;
	}

	public void addEmployee(Employee employee) {
		employeeRepo.save(employee);
	}

	public void updateEmployee(int id, Employee employeeDetails) throws EntityNotFoundException {
		Employee employee = employeeRepo.findById(id);
		CheckerClass.ifEmptyThrowException(employee);

		employee.setName(employeeDetails.getName());
		employee.setPassword(employeeDetails.getPassword());
		employee.setJob(employeeDetails.getJob());
		employee.setDevelopmentArea(employeeDetails.getDevelopmentArea());
		employee.setSkill(employeeDetails.getSkill());
		employee.setStartDate(employeeDetails.getStartDate());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setStartDate(employeeDetails.getLastLoginDate());

		employeeRepo.save(employee);
	}

	public void deleteEmployee(int id) throws EntityNotFoundException {
		Employee employee = employeeRepo.findById(id);
		CheckerClass.ifEmptyThrowException(employee);

		employeeRepo.delete(employee);
	}

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
}