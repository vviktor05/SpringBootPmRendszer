package com.pmrendszer.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.pmrendszer.TestContext;
import com.pmrendszer.TestData;
import com.pmrendszer.domain.Project;

@TestMethodOrder(OrderAnnotation.class)
public class ProjectApiControllerIntegrationTest extends TestContext {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void unknown_apiURL_shouldReturnUnauthorized() throws Exception {
		mockMvc.perform(get("/api")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getAllProjects_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(7)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is("Nyílvántartó rendszer")));
	}

	@Test
	@WithMockUser(roles = "TEAM_LEADER")
	public void teamLeader_getAllProjects_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/team_leader/projects")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "TEAM_LEADER")
	public void teamLeader_getAllProjects_shouldReturnForbidden() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects")).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "DEVELOPER")
	public void developer_getAllProjects_shouldReturnForbidden() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "DEVELOPER")
	public void developer_getAllProjects_shouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/api/developer/projects")).andExpect(status().isNotFound());
	}

	@Test
	public void unknown_getAllProjects_shouldReturnUnauthorized() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getActiveProjects_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/active"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(3)))
			.andExpect(jsonPath("$[1].id", is(4)))
			.andExpect(jsonPath("$[1].name", is("Business Card Designer ")));
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getNumberOfActiveProjects_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/active/number_of"))
//			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", is(3)));
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectById_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/id/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$.name", is("Nyílvántartó rendszer")));
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectById_shouldReturnBadRequest() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/id/{id}", -1))
			.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectById_shouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/id/{id}", 1111))
			.andExpect(status().isNotFound())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectsByName_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/name/{name}", "Nyílvántartó"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is("Nyílvántartó rendszer")));
	}

	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectsByDetailedSearch_ShouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/search").param("developmentAreaId", "1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));
		
		mockMvc.perform(get("/api/project_manager/projects/search").param("orderDateMin", "2020-02-20"))
		.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_getProjectsByDetailedSearch_ShouldReturnBadRequest() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/search").param("developmentAreaId", "Teszt"))
			.andExpect(status().isBadRequest());
		
		mockMvc.perform(get("/api/project_manager/projects/search").param("orderDateMin", "2020.02.02"))
		.andExpect(status().isBadRequest());
		
		mockMvc.perform(get("/api/project_manager/projects/search").param("orderDateMin", "2020 02 02"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_addProject_ShouldReturnBadRequest() throws Exception {
		Project project = TestData.PROJECT;
		project.setId(0);
		project.setName("");
		
		mockMvc.perform(post("/api/project_manager/projects")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(project)))
			.andExpect(status().isBadRequest());
		
		project.setName("Teszt projekt");
		project.setCustomer(null);
		
		mockMvc.perform(post("/api/project_manager/projects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(project)))
				.andExpect(status().isBadRequest());

		project.setCustomer(TestData.CUSTOMER);
		project.setOrderDate(null);
		
		mockMvc.perform(post("/api/project_manager/projects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(project)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	@Order(1)
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_addProject_ShouldReturnOk() throws Exception {
		Project project = TestData.PROJECT;
		project.setId(0);
		project.setName("Teszt projekt");
		
		MvcResult result = mockMvc.perform(post("/api/project_manager/projects")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(project)))
			.andExpect(status().isOk())
			.andReturn();
		
		Project resultProject = toObj(result.getResponse().getContentAsString(), Project.class);
		
		assertTrue(resultProject.getName().equals(project.getName()));
		assertTrue(resultProject.getId() == 8);
	}
	
	@Test
	@Order(2)
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_updateProject_ShouldReturnOk() throws Exception {
		Project project = TestData.PROJECT;
		project.setId(0);
		project.setName("Teszt projekt 2");
		
		mockMvc.perform(put("/api/project_manager/projects/{id}", 8)
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(project)))
			.andExpect(status().isOk());
	}
	
	@Test
	@Order(3)
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManager_deleteProject_ShouldReturnOk() throws Exception {
		mockMvc.perform(delete("/api/project_manager/projects/{id}", 8))
			.andExpect(status().isOk());
	}

//	@Test
//	@WithMockUser(roles = "DEVELOPER")
//	public void developer_getMyProjects_ShouldReturnOk() throws Exception {
//		mockMvc.perform(get("/api/developer/projects")).andExpect(status().isOk());
//	}
}
