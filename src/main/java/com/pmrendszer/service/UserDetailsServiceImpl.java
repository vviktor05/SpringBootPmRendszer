package com.pmrendszer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.pmrendszer.repository.EmployeeRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserDetailsImpl(employeeRepo.findByEmail(username));
	}

	@Autowired
	public void setEmployeeRepo(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
}