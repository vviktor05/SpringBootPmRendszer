package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.TeamMembership;
import com.pmrendszer.repository.TeamMembershipRepo;

@Service
public class TeamMembershipService {
	private TeamMembershipRepo teamMembershipRepo;

	public List<TeamMembership> getAllTeamMemberships() {
		return teamMembershipRepo.findAll();
	}

	public TeamMembership getTeamMembershipById(int teamId, int employeeId) throws EntityNotFoundException {
		TeamMembership teamMemberShip = teamMembershipRepo.findByTeamIdAndEmployeeId(teamId, employeeId);
		CheckerClass.ifEmptyThrowException(teamMemberShip);

		return teamMemberShip;
	}

	public void addTeamMembership(TeamMembership teamMembership) {
		teamMembershipRepo.save(teamMembership);
	}

	public void updateTeamMembership(int teamId, int employeeId, TeamMembership teamMembershipDetails)
			throws EntityNotFoundException {
		TeamMembership teamMemberShip = teamMembershipRepo.findByTeamIdAndEmployeeId(teamId, employeeId);
		CheckerClass.ifEmptyThrowException(teamMemberShip);

		teamMemberShip.setTeam(teamMembershipDetails.getTeam());
		teamMemberShip.setEmployee(teamMembershipDetails.getEmployee());
		teamMembershipRepo.save(teamMemberShip);
	}

	public void deleteTeamMembership(int teamId, int employeeId) throws EntityNotFoundException {
		TeamMembership teamMemberShip = teamMembershipRepo.findByTeamIdAndEmployeeId(teamId, employeeId);
		CheckerClass.ifEmptyThrowException(teamMemberShip);

		teamMembershipRepo.delete(teamMemberShip);
	}

	@Autowired
	public void setTeamMembershipRepo(TeamMembershipRepo teamMembershipRepo) {
		this.teamMembershipRepo = teamMembershipRepo;
	}
}