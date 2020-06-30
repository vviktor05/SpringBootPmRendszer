package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	public Employee getEmployeeByEmail(String email) throws EntityNotFoundException {
		Employee employee = employeeRepo.findByEmail(email);
		CheckerClass.ifEmptyThrowException(employee);

		return employee;
	}

	public List<Employee> getTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = employeeRepo.findTeamMembers(id);
		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	public List<Employee> getNotTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = employeeRepo.findNotTeamMembers(id);
		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	public void addEmployee(Employee employee) {
		employee.setPassword(new BCryptPasswordEncoder(10).encode(employee.getPassword()));
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