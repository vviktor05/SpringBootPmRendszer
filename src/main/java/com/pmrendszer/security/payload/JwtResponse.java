package com.pmrendszer.security.payload;

import com.pmrendszer.domain.Employee;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Employee employee;
	private String role;
	
	public JwtResponse(String token, Employee employee, String role) {
		this.token = token;
		this.employee = employee;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRole() {
		return role;
	}
}