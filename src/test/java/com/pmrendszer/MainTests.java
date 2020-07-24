package com.pmrendszer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.pmrendszer.controller.api.ProjectApiController;
import com.pmrendszer.service.ProjectService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class MainTests {

	@Autowired
	private ProjectApiController projectApiController;

	@MockBean
	private ProjectService projectService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
		assertThat(projectApiController).isNotNull();
	}

	@Test
	public void mainPage_shouldReturnNotFound() throws Exception {
		mockMvc.perform(get("/")).andExpect(status().isNotFound());
	}

	@Test
	public void apiURL_shouldReturnUnauthorized() throws Exception {
		mockMvc.perform(get("/api")).andExpect(status().isUnauthorized());
	}

}