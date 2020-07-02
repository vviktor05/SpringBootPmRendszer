package com.pmrendszer.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthenticatedUser {

	public static String getEmail() {
		return getAuthentication().getName();
	}
	
	public static boolean isRole(String role) {
		return getAuthentication().getAuthorities().contains(role);
	}
	
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}