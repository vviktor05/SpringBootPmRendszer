package com.pmrendszer.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Employee;
import com.pmrendszer.domain.Project;
import com.pmrendszer.repository.ProjectRepo;

@Service
public class ProjectService {
	private ProjectRepo projectRepo;
	private EmployeeService employeeService;
	private final int ACTIVE_STATUS_ID = 3;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	public List<Project> getActiveProjects() {
		return projectRepo.findByStatusId(ACTIVE_STATUS_ID);
	}
	
	public int getNumberOfActiveProjects() {
		return projectRepo.countProjectsByStatusId(ACTIVE_STATUS_ID);
	}

	public Project getProjectById(int id) throws EntityNotFoundException {
		Project project = projectRepo.findById(id);
		CheckerClass.ifEmptyThrowException(project);

		return project;
	}

	public List<Project> getProjectsByName(String name) throws EntityNotFoundException {
		List<Project> projects = projectRepo.findByNameContainingOrderByName(name);
		CheckerClass.ifEmptyThrowException(projects);

		return projects;
	}

	public List<Project> getProcjetsByDetailedSearch(int customerId, int developmentAreaId, String orderDateMin,
			String orderDateMax, int projectStatusId, int priorityId, int projectLeaderId, int statusId)
			throws EntityNotFoundException, Exception {

		Date oDateMin = CheckerClass.parseDate(orderDateMin, CheckerClass.DATE_FORMAT);
		Date oDateMax = CheckerClass.parseDate(orderDateMax, CheckerClass.DATE_FORMAT);

		List<Project> projects;
		if (oDateMin != null && oDateMax != null) {
			projects = projectRepo.detailedSearch(customerId, developmentAreaId, oDateMin, oDateMax, projectStatusId,
					priorityId, projectLeaderId, statusId);

			CheckerClass.ifEmptyThrowException(projects);
		} else {
			throw new Exception("Hiba a rendelési dátum átalakításánál!");
		}

		return projects;
	}

	public Project addProject(Project project) {
		return projectRepo.save(project);
	}

	public Project updateProject(int id, Project projectDetails) throws EntityNotFoundException {
		Project project = projectRepo.findById(id);
		CheckerClass.ifEmptyThrowException(project);

		return projectRepo.save(updateProjectDetails(project, projectDetails));
	}

	public void deleteProject(int id) throws EntityNotFoundException {
		Project project = projectRepo.findById(id);
		CheckerClass.ifEmptyThrowException(project);

		projectRepo.delete(project);
	}

	public List<Project> getMyProjects() {
		return projectRepo.findMyProjects(getAuthenticatedEmployee().getId());
	}

	public List<Project> getMyActiveProjects() {
		return projectRepo.findMyActiveProjects(getAuthenticatedEmployee().getId());
	}

	public Project getMyProjectById(int id) {
		Project project = projectRepo.findMyProjectById(id, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(project);

		return project;
	}

	public List<Project> getMyProjectsByName(String name) {
		List<Project> projects = projectRepo.findMyProjectsByName(name, getAuthenticatedEmployee().getId());
		CheckerClass.ifEmptyThrowException(projects);

		return projects;
	}

	public List<Project> getMyProcjetsByDetailedSearch(int customerId, int developmentAreaId, String orderDateMin,
			String orderDateMax, int projectStatusId, int priorityId, int projectLeaderId, int statusId)
			throws Exception {

		Date oDateMin = CheckerClass.parseDate(orderDateMin, CheckerClass.DATE_FORMAT);
		Date oDateMax = CheckerClass.parseDate(orderDateMax, CheckerClass.DATE_FORMAT);

		List<Project> projects;
		if (oDateMin != null && oDateMax != null) {
			projects = projectRepo.findMyProjectsBydetailedSearch(customerId, developmentAreaId, oDateMin, oDateMax,
					projectStatusId, priorityId, projectLeaderId, statusId, getAuthenticatedEmployee().getId());

			CheckerClass.ifEmptyThrowException(projects);
		} else {
			throw new Exception("Hiba a rendelési dátum átalakításánál!");
		}

		return projects;
	}

	private Employee getAuthenticatedEmployee() {
		return employeeService.getAuthenticatedEmployee();
	}

	private Project updateProjectDetails(Project project, Project projectDetails) {
		project.setName(projectDetails.getName());
		project.setCustomer(projectDetails.getCustomer());
		project.setDevelopmentArea(projectDetails.getDevelopmentArea());
		project.setOrderDate(projectDetails.getOrderDate());
		project.setDeadline(projectDetails.getDeadline());
		project.setProjectStatus(projectDetails.getProjectStatus());
		project.setPriority(projectDetails.getPriority());
		project.setStatus(projectDetails.getStatus());
		project.setDescription(projectDetails.getDescription());
		project.setUpdateMode(true);
		return project;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setProjectRepo(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}
	
	public int getActiveStatusId() {
		return ACTIVE_STATUS_ID;
	}
}