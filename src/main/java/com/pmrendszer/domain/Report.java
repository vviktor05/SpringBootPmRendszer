package com.pmrendszer.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
	@Column(columnDefinition = "timestamp")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordingDate;
	@NotNull
	@ManyToOne
	private Employee employee;
	@NotNull
	@Column(columnDefinition = "text")
	private String text;
	@Transient
	@JsonIgnore
	private boolean updateMode;

	public Report() {
		;
	}

	public Report(int id, Project project, LocalDateTime recordingDate, Employee employee, String text) {
		this.id = id;
		this.project = project;
		this.recordingDate = recordingDate;
		this.employee = employee;
		this.text = text;
	}

	@JsonIgnore
	@AssertTrue(message = "{id.valid}")
	public boolean isValidId() {
		if (updateMode) {
			return true;
		}
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

	public LocalDateTime getRecordingDate() {
		return recordingDate;
	}

	public void setRecordingDate(LocalDateTime recordingDate) {
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

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
}