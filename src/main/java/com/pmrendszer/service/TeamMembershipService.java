package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.domain.TeamMembership;
import com.pmrendszer.repository.TeamMembershipRepo;

@Service
public class TeamMembershipService {
	private TeamMembershipRepo teamMembershipRepo;

	public List<TeamMembership> getAllTeamMemberships() {
		return teamMembershipRepo.findAll();
	}

	public List<Employee> getTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = teamMembershipRepo.findTeamMembers(id);
		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	public List<Employee> getNotTeamMembers(int id) throws EntityNotFoundException {
		List<Employee> employees = teamMembershipRepo.findNotTeamMembers(id);
		CheckerClass.ifEmptyThrowException(employees);

		return employees;
	}

	@Autowired
	public void setTeamMembershipRepo(TeamMembershipRepo teamMembershipRepo) {
		this.teamMembershipRepo = teamMembershipRepo;
	}
}