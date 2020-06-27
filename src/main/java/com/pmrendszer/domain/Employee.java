package com.pmrendszer.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	private String name;
	private String password;
	@ManyToOne
	private Job job;
	@ManyToOne
	private DevelopmentArea developmentArea;
	@ManyToOne
	private Skill skill;
	@Column(columnDefinition = "date")
	private String startDate;
	private String phoneNumber;
	@Column(columnDefinition = "timestamp")
	private String lastLoginDate;

	public Employee() {
		;
	}

	public Employee(int id, String name, String password, Job job, DevelopmentArea developmentArea, Skill skill,
			String startDate, String phoneNumber, String lastLoginDate) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.job = job;
		this.developmentArea = developmentArea;
		this.skill = skill;
		this.startDate = startDate;
		this.phoneNumber = phoneNumber;
		this.lastLoginDate = lastLoginDate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public DevelopmentArea getDevelopmentArea() {
		return developmentArea;
	}

	public void setDevelopmentArea(DevelopmentArea developmentArea) {
		this.developmentArea = developmentArea;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
}