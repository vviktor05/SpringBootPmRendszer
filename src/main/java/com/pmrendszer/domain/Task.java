package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Tasks")
public class Task {
	@GeneratedValue
	@Column(columnDefinition = "serial")
	@Id
	private int id;
	private String topic;
	private String deadline;
	@ManyToOne
	private Employee teamLeader;
	@ManyToOne
	private Project project;
	@ManyToOne
	private Status status;
	@Column(columnDefinition = "text")
	private String description;

	public Task() {
		;
	}

	public Task(int id, String topic, String deadline, Employee teamLeader, Project project, Status status,
			String description) {
		this.id = id;
		this.topic = topic;
		this.deadline = deadline;
		this.teamLeader = teamLeader;
		this.project = project;
		this.status = status;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Employee getTeamLeader() {
		return teamLeader;
	}

	public void setTeamLeader(Employee teamLeader) {
		this.teamLeader = teamLeader;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}