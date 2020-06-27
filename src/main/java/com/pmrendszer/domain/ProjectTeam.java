package com.pmrendszer.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Projects_teams")
@IdClass(ProjectTeam.class)
public class ProjectTeam implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Team team;
	@Id
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Project project;

	public ProjectTeam() {
		;
	}

	public ProjectTeam(Team team, Project project) {
		this.team = team;
		this.project = project;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}