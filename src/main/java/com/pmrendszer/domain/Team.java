package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmrendszer.service.CheckerClass;

@Entity(name = "Teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@Size(max = 100, message = "{team.name.max}")
	private String name;
	@NotNull
	@ManyToOne
	private Employee teamLeader;
	@Transient
	@JsonIgnore
	private boolean updateMode;

	public Team() {
		;
	}

	public Team(int id, String name, Employee teamLeader) {
		this.id = id;
		this.name = name;
		this.teamLeader = teamLeader;
	}

	@JsonIgnore
	@AssertTrue(message = "{id.valid}")
	public boolean isValidId() {
		if (updateMode) {
			return true;
		}
		return id == 0;
	}
	
	@JsonIgnore
	@AssertTrue(message = "{team.name.min}")
	public boolean isValidNameMinLength() {
		return CheckerClass.isValidMinLength(name, 5);
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

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
}