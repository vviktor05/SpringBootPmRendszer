package com.pmrendszer.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Projects")
public class Project {
	@GeneratedValue
	@Column(columnDefinition = "serial")
	@Id
	private int id;
	private String name;
	@ManyToOne
	private Customer customer;
	@ManyToOne
	private DevelopmentArea developmentArea;
	private String orderDate;
	private String deadline;
	@ManyToOne
	private ProjectStatus projectStatus;
	@ManyToOne
	private Priority priority;
	@ManyToOne
	private Employee projectLeader;
	@ManyToOne
	private Status status;
	@Column(columnDefinition = "text")
	private String description;
	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	private List<Team> teams;

	public Project() {
		;
	}

	public Project(int id, String name, Customer customer, DevelopmentArea developmentArea, String orderDate,
			String deadline, ProjectStatus projectStatus, Priority priority, Employee projectLeader, Status status,
			String description, List<Team> teams) {
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
		this.teams = teams;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
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

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
}