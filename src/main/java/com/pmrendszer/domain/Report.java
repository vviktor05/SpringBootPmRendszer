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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "Reports")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@ManyToOne
	private Project project;
	@NotNull
	@Column(columnDefinition = "timestamp")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date recordingDate;
	@NotNull
	@ManyToOne
	private Employee employee;
	@NotNull
	@Column(columnDefinition = "text")
	private String text;

	public Report() {
		;
	}

	public Report(int id, Project project, Date recordingDate, Employee employee, String text) {
		this.id = id;
		this.project = project;
		this.recordingDate = recordingDate;
		this.employee = employee;
		this.text = text;
	}

	@JsonIgnore
	@AssertTrue(message = "{id.valid}")
	public boolean isValidId() {
		return id == 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(Date recordingDate) {
		this.recordingDate = recordingDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}