package com.pmrendszer.service;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pmrendszer.domain.Employee;

public class UserDetailsImpl implements UserDetails {
	private Employee employee;

	public UserDetailsImpl(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(employee.getJob().getName()));

		return authorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}