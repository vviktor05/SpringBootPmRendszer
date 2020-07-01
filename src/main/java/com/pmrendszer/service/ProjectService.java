package com.pmrendszer.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Project;
import com.pmrendszer.repository.ProjectRepo;

@Service
public class ProjectService {
	private ProjectRepo projectRepo;
	private final int ACTIVE_STATUS_ID = 3;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	public List<Project> getActiveProjects() {
		return projectRepo.findByStatusId(ACTIVE_STATUS_ID);
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

		project.setName(projectDetails.getName());
		project.setCustomer(projectDetails.getCustomer());
		project.setDevelopmentArea(projectDetails.getDevelopmentArea());
		project.setOrderDate(projectDetails.getOrderDate());
		project.setDeadline(projectDetails.getDeadline());
		project.setProjectStatus(projectDetails.getProjectStatus());
		project.setPriority(projectDetails.getPriority());
		project.setProjectLeader(projectDetails.getProjectLeader());
		project.setStatus(projectDetails.getStatus());
		project.setDescription(projectDetails.getDescription());
		return projectRepo.save(project);
	}

	public void deleteProject(int id) throws EntityNotFoundException {
		Project project = projectRepo.findById(id);
		CheckerClass.ifEmptyThrowException(project);

		projectRepo.delete(project);
	}

	public List<Project> getMyProjects() {
		return null;
	}

	public List<Project> getMyActiveProjects() {
		return null;
	}

	public Project getMyProjectById(int id) {
		return null;
	}

	public List<Project> getMyProjectsByName(String name) {
		return null;
	}

	public List<Project> getMyProcjetsByDetailedSearch(int customerId, int developmentAreaId, String orderDateMin,
			String orderDateMax, int projectStatusId, int priorityId, int projectLeaderId, int statusId)
			throws Exception {

//		Date oDateMin = CheckerClass.parseDate(orderDateMin, CheckerClass.DATE_FORMAT);
//		Date oDateMax = CheckerClass.parseDate(orderDateMax, CheckerClass.DATE_FORMAT);
//
//		List<Project> projects;
//		if (oDateMin != null && oDateMax != null) {
//			projects = projectRepo.detailedSearchInMy(customerId, developmentAreaId, oDateMin, oDateMax,
//					projectStatusId, priorityId, projectLeaderId, statusId);
//
//			CheckerClass.ifEmptyThrowException(projects);
//		} else {
//			throw new Exception("Hiba a rendelési dátum átalakításánál!");
//		}

		return null;
	}

	@Autowired
	public void setProjectRepo(ProjectRepo projectRepo) {
		this.projectRepo = projectRepo;
	}
}