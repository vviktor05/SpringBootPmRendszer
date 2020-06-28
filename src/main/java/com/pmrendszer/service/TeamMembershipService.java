package com.pmrendszer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.domain.TeamMembership;
import com.pmrendszer.repository.TeamMembershipRepo;

@Service
public class TeamMembershipService {
	private TeamMembershipRepo teamMembershipRepo;

	public List<TeamMembership> getAllTeamMemberships() {
		return teamMembershipRepo.findAll();
	}

	@Autowired
	public void setTeamMembershipRepo(TeamMembershipRepo teamMembershipRepo) {
		this.teamMembershipRepo = teamMembershipRepo;
	}
}