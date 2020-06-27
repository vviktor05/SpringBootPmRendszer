package com.pmrendszer.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Team_memberships")
@IdClass(TeamMembership.class)
public class TeamMembership implements Serializable{
	@Id
	@ManyToOne
	@JoinColumn(insertable=false, updatable=false)
	private Employee employee;
	@Id
	@ManyToOne
	@JoinColumn(insertable=false, updatable=false)
	private Team team;

	public TeamMembership() {
		;
	}

	public TeamMembership(Employee employee, Team team) {
		this.employee = employee;
		this.team = team;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}