package com.pmrendszer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pmrendszer.TestData;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.repository.EmployeeRepo;

public class EmployeeServiceTest {

	@Mock
	private EmployeeRepo employeeRepo;
	
	private EmployeeService employeeService;

	private Employee employee;
	private List<Employee> allEmployee;

	public EmployeeServiceTest() {
		MockitoAnnotations.initMocks(this);
		employeeService = new EmployeeService();
		employeeService.setEmployeeRepo(employeeRepo);

		employee = TestData.EMPLOYEE;
		allEmployee = Arrays.asList(employee);
	}
	
	@Test
	public void getAllEmployees_thenReturn1Records() {
		when(employeeRepo.findAll()).thenReturn(allEmployee);

		List<Employee> allEmployees = employeeService.getAllEmployees();

		verify(employeeRepo, times(1)).findAll();
		reset(employeeRepo);

		assertThat(allEmployees).hasSize(1);
	}
	
	@Test
	public void addEmployee_thenReturnSavedEmployee() {
		when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

		Employee savedEmployee = employeeService.addEmployee(employee);

		verify(employeeRepo, times(1)).save(any(Employee.class));
		reset(employeeRepo);

		assertThat(savedEmployee.getId()).isNotZero();
	}
	
	@Test
	public void deleteProject() throws EntityNotFoundException, Exception {
		int employeeId = 2;
		employee.setId(2);
		
		when(employeeRepo.findById(employeeId)).thenReturn(employee);
		doNothing().when(employeeRepo).delete(employee);

		employeeService.deleteEmployee(employeeId);
		
		verify(employeeRepo, times(1)).findById(anyInt());
		verify(employeeRepo, times(1)).delete(any(Employee.class));
		reset(employeeRepo);
		employee.setId(1);
	}
	
	@Test
	public void deleteProject_throwEntityNotFoundException() throws EntityNotFoundException, Exception {
		int employeeId = 1;
		
		when(employeeRepo.findById(employeeId)).thenReturn(null);
		
		Exception exception = assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
		
		verify(employeeRepo, times(1)).findById(anyInt());
		verify(employeeRepo, times(0)).delete(any(Employee.class));
		reset(employeeRepo);
		
		String expectedMessage = "Nincs találat!";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
}
