package com.pmrendszer.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pmrendszer.service.CheckerClass;

@Entity(name = "Employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@NotNull
	@Size(max = 50, message = "{employee.name.max}")
	private String name;
	@NotNull
	@Email(message = "{email.valid}")
	@Size(max = 100, message = "{email.max}")
	@Column(unique=true)
	private String email;
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	@NotNull
	@ManyToOne
	private Job job;
	@NotNull
	@ManyToOne
	private DevelopmentArea developmentArea;
	@NotNull
	@ManyToOne
	private Skill skill;
	@NotNull
	@Column(columnDefinition = "date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@Size(max = 15, message = "{employee.phoneNumber.max}")
	@NotNull
	private String phoneNumber;
	@Nullable
	@Column(columnDefinition = "timestamp")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoginDate;
	@Transient
	@JsonIgnore
	private boolean updateMode;
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TeamMembership> teamMemberships;
	@JsonIgnore
	@OneToMany(mappedBy = "teamLeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> tasks;
	@JsonIgnore
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Report> reports;
	@JsonIgnore
	@OneToMany(mappedBy = "projectLeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Project> projects;
	@JsonIgnore
	@OneToMany(mappedBy = "teamLeader", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Team> teams;
	
	public Employee() {
		;
	}

	public Employee(int id, String name, String email, String password, Job job, DevelopmentArea developmentArea, Skill skill,
			LocalDate startDate, String phoneNumber, LocalDateTime lastLoginDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.job = job;
		this.developmentArea = developmentArea;
		this.skill = skill;
		this.startDate = startDate;
		this.phoneNumber = phoneNumber;
		this.lastLoginDate = lastLoginDate;
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
	@AssertTrue(message = "{employee.name.min}")
	public boolean isValidNameMinLength() {
		return CheckerClass.isValidMinLength(name, 5);
	}

	@JsonIgnore
	@AssertTrue(message = "{employee.phoneNumber.min}")
	public boolean isValidPhoneNumberMinLength() {
		return CheckerClass.isValidMinLength(phoneNumber, 3);
	}

	@JsonIgnore
	@AssertTrue(message = "{employee.name.valid}")
	public boolean isValidName() {
		return CheckerClass.isValidName(name);
	}

	@JsonIgnore
	@AssertTrue(message = "{employee.phoneNumber.valid}")
	public boolean isValidPhoneNumber() {
		return CheckerClass.isValidPhoneNumber(phoneNumber);
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public boolean isUpdateMode() {
		return updateMode;
	}

	public void setUpdateMode(boolean updateMode) {
		this.updateMode = updateMode;
	}
}