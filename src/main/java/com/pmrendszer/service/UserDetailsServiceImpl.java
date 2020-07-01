package com.pmrendszer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.repository.EmployeeRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepo.findByEmail(username);
		
		if (employee == null) {
			throw new UsernameNotFoundException("Hibás bejelentkezési adatok!");
		}
		return new UserDetailsImpl(employee);
	}

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
}