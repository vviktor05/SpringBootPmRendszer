package com.pmrendszer;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pmrendszer.domain.Project;
import com.pmrendszer.service.ProjectService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ProjectApiTests {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManagerGetAllProjects_shouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects")).andExpect(status().isOk());
	}

	@Test
	public void developerGetAllProjects_ShouldReturnForbidden() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects").with(httpBasic("developer@gmail.com", "admin")))
				.andExpect(status().isForbidden());
	}
	
	@Test
	public void developerGetMyProjects_ShouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/developer/projects").with(httpBasic("developer@gmail.com", "admin")))
				.andExpect(status().isOk());
	}

	RestTemplate restTemplate = new RestTemplate();

	@Test
	@WithMockUser(roles = "TEAM_LEADER")
	public void teamLeaderAddProject_ShouldReturnForbidden() throws Exception {
		Project project = projectService.getProjectById(1);
		project.setId(0);

		mockMvc.perform(post("/api/project_manager/projects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(toJson(project)))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(roles = "PROJECT_MANAGER")
	public void projectManagerGetProjectsByDetailedSearch_ShouldReturnOk() throws Exception {
		mockMvc.perform(get("/api/project_manager/projects/search?developmentAreaId=1")).andExpect(status().isOk());
	}
	
	public static String toJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}