package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reports")
public class Report {
	@GeneratedValue
	@Column(columnDefinition = "serial")
	@Id
	private int id;
	@ManyToOne
	private Project project;
	private String recordingDate;
	@ManyToOne
	private Employee employee;
	@Column(columnDefinition = "text")
	private String text;

	public Report() {
		;
	}

	public Report(int id, Project project, String recordingDate, Employee employee, String text) {
		this.id = id;
		this.project = project;
		this.recordingDate = recordingDate;
		this.employee = employee;
		this.text = text;
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

	public String getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(String recordingDate) {
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