package com.pmrendszer.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TeamMembershipKey implements Serializable {
	@Column(name = "employee_id")
	private int employee;
	@Column(name = "team_id")
	private int team;

	public TeamMembershipKey() {
		;
	}

	public TeamMembershipKey(int employee, int team) {
		this.employee = employee;
		this.team = team;
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}