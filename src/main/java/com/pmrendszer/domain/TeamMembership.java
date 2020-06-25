package com.pmrendszer.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "Team_memberships")
public class TeamMembership {
	@EmbeddedId
	TeamMembershipKey id;
	
	@ManyToOne
	@MapsId("employee_id")
    @JoinColumn(name = "employee_id")
	private Employee employee;
	private String startDate;
	private String endDate;
	@ManyToOne
	@MapsId("team_id")
    @JoinColumn(name = "team_id")
	private Team team;

	public TeamMembership() {
		;
	}

	public TeamMembership(Employee employee, String startDate, String endDate, Team team) {
		this.employee = employee;
		this.startDate = startDate;
		this.endDate = endDate;
		this.team = team;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}