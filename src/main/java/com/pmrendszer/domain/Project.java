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
	private Date orderDate;
	@NotNull
	@Column(columnDefinition = "date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
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

	public Project() {
		;
	}

	public Project(int id, String name, Customer customer, DevelopmentArea developmentArea, Date orderDate,
			Date deadline, ProjectStatus projectStatus, Priority priority, Employee projectLeader, Status status,
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
		return id == 0;
	}

	@JsonIgnore
	@AssertTrue(message = "{project.name.min}")
	public boolean isValidNameMinLength() {
		return CheckerClass.isValidMinLength(name, 5);
	}

	@JsonIgnore
	@AssertTrue(message = "{project.name.valid}")
	public boolean isValidName() {
		return CheckerClass.isValidName(name);
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
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
}