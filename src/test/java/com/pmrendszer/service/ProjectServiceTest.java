package com.pmrendszer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pmrendszer.TestData;
import com.pmrendszer.controller.api.error.EntityNotFoundException;
import com.pmrendszer.domain.Project;
import com.pmrendszer.repository.ProjectRepo;

public class ProjectServiceTest {

	@Mock
	private ProjectRepo projectRepo;
	
	private ProjectService projectService;

	private Project project1;
	private Project project2;
	private List<Project> allProjects;

	public ProjectServiceTest() {
		MockitoAnnotations.initMocks(this);
		projectService = new ProjectService();
		projectService.setProjectRepo(projectRepo);

		project1 = TestData.PROJECT;
		project2 = new Project();
		allProjects = Arrays.asList(project1, project2);
	}

	@Test
	public void getAllProjects_thenReturn2Records() {
		when(projectRepo.findAll()).thenReturn(allProjects);

		List<Project> allProjects = projectService.getAllProjects();

		verify(projectRepo, times(1)).findAll();
		reset(projectRepo);

		assertThat(allProjects).hasSize(2);
	}

	@Test
	public void getActiveProjects_thenReturn1Records() {
		int activeStatusId = projectService.getActiveStatusId();
		List<Project> expectedActiveProjects = Arrays.asList(project1);
		
		when(projectRepo.findByStatusId(activeStatusId)).thenReturn(expectedActiveProjects);

		List<Project> activeProjects = projectService.getActiveProjects();

		verify(projectRepo, times(1)).findByStatusId(activeStatusId);
		reset(projectRepo);

		assertThat(activeProjects).hasSize(1);
	}
	
	@Test
	public void addProject_thenReturnSavedProject() {
		when(projectRepo.save(any(Project.class))).thenReturn(project1);

		Project savedProject = projectService.addProject(project1);

		verify(projectRepo, times(1)).save(any(Project.class));
		reset(projectRepo);

		assertThat(savedProject.getId() != 0);
	}
	
	@Test
	public void getProcjetsByDetailedSearch_thenReturn1Records() throws EntityNotFoundException, Exception {
		List<Project> expectedProjects = Arrays.asList(project1);
		
		when(projectRepo.detailedSearch(eq(1), eq(1), any(Date.class), any(Date.class), eq(1),
				eq(1), eq(1), eq(1))).thenReturn(expectedProjects);

		List<Project> projects = projectService.getProcjetsByDetailedSearch(1, 1, "2020-04-03", "2020-07-03", 1,
				1, 1, 1);

		verify(projectRepo, times(1)).detailedSearch(eq(1), eq(1), any(Date.class), any(Date.class), eq(1),
				eq(1), eq(1), eq(1));
		reset(projectRepo);

		assertThat(projects).hasSize(1);
	}
	
	@Test
	public void getProcjetsByDetailedSearch_throwEntityNotFoundException() throws EntityNotFoundException, Exception {
		List<Project> expectedProjects = new ArrayList<>();
		
		when(projectRepo.detailedSearch(eq(1), eq(1), any(Date.class), any(Date.class), eq(1),
				eq(1), eq(1), eq(1))).thenReturn(expectedProjects);

		Exception exception = assertThrows(EntityNotFoundException.class, () -> projectService.getProcjetsByDetailedSearch(1, 1, "2020-04-03", "2020-07-03", 1,
				1, 1, 1));
		
		verify(projectRepo, times(1)).detailedSearch(eq(1), eq(1), any(Date.class), any(Date.class), eq(1),
				eq(1), eq(1), eq(1));
		reset(projectRepo);
		
		String expectedMessage = "Nincs találat!";
	    String actualMessage = exception.getMessage();

	    assertEquals(expectedMessage, actualMessage);
	}
	
//	@Test
//	public void deleteProject() throws EntityNotFoundException, Exception {
//		int projectId = 1;
//		
//		when(projectRepo.findById(projectId)).thenReturn(project1);
//		doNothing().when(projectRepo).delete(project1);
//
//		projectService.deleteProject(projectId);
//		
//		verify(projectRepo, times(1)).findById(anyInt());
//		verify(projectRepo, times(1)).delete(any(Project.class));
//		reset(projectRepo);
//	}
}