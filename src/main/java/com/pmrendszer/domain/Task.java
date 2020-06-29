package com.pmrendszer.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmrendszer.service.CheckerClass;

@Entity(name = "Tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@Size(max = 100, message = "{task.topic.max}")
	private String topic;
	@NotNull
	@Column(columnDefinition = "timestamp")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deadline;
	@NotNull
	@ManyToOne
	private Employee teamLeader;
	@NotNull
	@ManyToOne
	private Project project;
	@NotNull
	@ManyToOne
	private Status status;
	@Nullable
	@Column(columnDefinition = "text")
	private String description;

	public Task() {
		;
	}

	public Task(int id, String topic, Date deadline, Employee teamLeader, Project project, Status status,
			String description) {
		this.id = id;
		this.topic = topic;
		this.deadline = deadline;
		this.teamLeader = teamLeader;
		this.project = project;
		this.status = status;
		this.description = description;
	}
	
	@JsonIgnore
	@AssertTrue(message = "{task.id}")
	public boolean isValidId() {
		return id == 0;
	}

	@JsonIgnore
	@AssertTrue(message = "{task.topic.min}")
	public boolean isValidTopicMinLength() {
		return CheckerClass.isValidMinLength(topic, 5);
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

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
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