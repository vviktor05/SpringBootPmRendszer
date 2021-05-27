package com.pmrendszer.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmrendszer.service.CheckerClass;

@Entity(name = "Projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@Size(max = 100, message = "{project.name.max}")
	private String name;
	@NotNull
	@ManyToOne
	private Customer customer;
	@NotNull
	@ManyToOne
	private DevelopmentArea developmentArea;
	@NotNull
	@Column(columnDefinition = "date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	@NotNull
	@Column(columnDefinition = "date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate deadline;
	@NotNull
	@ManyToOne
	private ProjectStatus projectStatus;
	@ManyToOne
	private Priority priority;
	@NotNull
	@ManyToOne
	private Employee projectLeader;
	@NotNull
	@ManyToOne
	private Status status;
	@Nullable
	@Column(columnDefinition = "text")
	private String description;
	@Transient
	@JsonIgnore
	private boolean updateMode;
	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProjectTeam> projectTeams;
	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;
	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Report> reports;

	public Project() {
		;
	}

	public Project(int id, String name, Customer customer, DevelopmentArea developmentArea, LocalDate orderDate,
			LocalDate deadline, ProjectStatus projectStatus, Priority priority, Employee projectLeader, Status status,
			String description) {
		this.id = id;
		this.name = name;
		this.customer = customer;
		this.developmentArea = developmentArea;
		this.orderDate = orderDate;
		this.deadline = deadline;
		this.projectStatus = projectStatus;
		this.priority = priority;
		this.projectLeader = projectLeader;
		this.status = status;
		this.description = description;
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
	@AssertTrue(message = "{project.name.min}")
	public boolean isValidNameMinLength() {
		return CheckerClass.isValidMinLength(name, 5);
	}
	
	@JsonIgnore
	@AssertTrue(message = "{project.dates.valid}")
	public boolean isValidDates() {
		return CheckerClass.isValidDates(orderDate, deadline);
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DevelopmentArea getDevelopmentArea() {
		return developmentArea;
	}

	public void setDevelopmentArea(DevelopmentArea developmentArea) {
		this.developmentArea = developmentArea;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Employee getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(Employee projectLeader) {
		this.projectLeader = projectLeader;
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

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
}