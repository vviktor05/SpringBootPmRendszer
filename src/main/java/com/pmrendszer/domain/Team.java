package com.pmrendszer.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Teams")
public class Team {
	@GeneratedValue
	@Column(columnDefinition = "serial")
	@Id
	private int id;
	private String name;
	@OneToOne
	private Employee teamLeader;
	@OneToMany(mappedBy = "team")
	@JsonIgnore
	private List<TeamMembership> teamMemberships;
	@ManyToMany()
	@JoinTable(name = "Projects_teams", 
	joinColumns = @JoinColumn(name = "team_id"), 
	inverseJoinColumns = @JoinColumn(name = "project_id"))
	@JsonIgnore
	private List<Project> projects;

	public Team() {
		;
	}
	
	public Team(int id, String name, Employee teamLeader) {
		this.id = id;
		this.name = name;
		this.teamLeader = teamLeader;
	}

	public Team(int id, String name, Employee teamLeader, List<TeamMembership> teamMemberships,
			List<Project> projects) {
		this.id = id;
		this.name = name;
		this.teamLeader = teamLeader;
		this.teamMemberships = teamMemberships;
		this.projects = projects;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Employee teamLeader) {
		this.teamLeader = teamLeader;
	}

	public List<TeamMembership> getTeamMemberships() {
		return teamMemberships;
	}

	public void setTeamMemberships(List<TeamMembership> teamMemberships) {
		this.teamMemberships = teamMemberships;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}