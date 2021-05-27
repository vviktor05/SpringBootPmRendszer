package com.pmrendszer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	public int getNumberOfEmployees() {
		return employeeRepo.countEmployees();
	}

	public Employee getEmployeeByEmail(String email) throws EntityNotFoundException {
		Employee employee = employeeRepo.findByEmail(email);
		CheckerClass.ifEmptyThrowException(employee);

		return employee;
	}
	
	public List<Employee> getAllTeamLeaderEmployees() {
		return employeeRepo.findAllTeamLeader();
	}

	public List<Employee> getTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = employeeRepo.findTeamMembers(id);
//		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	public List<Employee> getNotTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = employeeRepo.findNotTeamMembers(id);
//		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	public Employee addEmployee(Employee employee) {
		String startDate = employee.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		employee.setPassword(new BCryptPasswordEncoder(10).encode(startDate));
		
		return employeeRepo.save(employee);
	}

	public Employee updateEmployee(int id, Employee employeeDetails) throws EntityNotFoundException {
		Employee employee = employeeRepo.findById(id);
		CheckerClass.ifEmptyThrowException(employee);

		return employeeRepo.save(updateEmployeeDetails(employee, employeeDetails));
	}
	
	public Employee updateEmployeeLastLoginDate(Employee employee) {
		employee.setLastLoginDate(LocalDateTime.now());
		employee.setUpdateMode(true);
		
		return employeeRepo.save(employee);
	}

	public void deleteEmployee(int id) throws EntityNotFoundException {
		Employee employee = employeeRepo.findById(id);
		CheckerClass.ifEmptyThrowException(employee);

		if(id != 1) {
			employeeRepo.delete(employee);
		}
	}

	public void updateTeamMemberships(int teamId, List<Employee> employees) {
		employeeRepo.deleteTeamMemberships(teamId);
		
		Employee employee;
		for (int i = 0; i < employees.size(); i++) {
			employee = employees.get(i);
			employee.setUpdateMode(true);
			employeeRepo.saveTeamMemberships(employee.getId(), teamId);
		}
	}
	
	public List<Employee> getMyEmployees() {
		return employeeRepo.findMyEmployees(getAuthenticatedEmployee().getId());
	}

	public Employee getMyEmployeeById(int id) throws EntityNotFoundException {
		Employee employee = employeeRepo.findMyEmployeeById(id, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(employee);

		return employee;
	}

	public Employee getAuthenticatedEmployee() {
		return getEmployeeByEmail(AuthenticatedUser.getEmail());
	}

	private Employee updateEmployeeDetails(Employee employee, Employee employeeDetails) {
		employee.setName(employeeDetails.getName());
		employee.setJob(employeeDetails.getJob());
		employee.setDevelopmentArea(employeeDetails.getDevelopmentArea());
		employee.setSkill(employeeDetails.getSkill());
		employee.setStartDate(employeeDetails.getStartDate());
		employee.setPhoneNumber(employeeDetails.getPhoneNumber());
		employee.setUpdateMode(true);
		return employee;
	}

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
}