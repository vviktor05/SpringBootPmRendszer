package com.pmrendszer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
public class TestContext {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> T toObj(String obj, Class<T> aClass) {
		try {
			return objectMapper.readValue(obj, aClass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}