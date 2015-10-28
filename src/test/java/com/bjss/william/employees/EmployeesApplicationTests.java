package com.bjss.william.employees;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bjss.william.employees.controller.EmployeesController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmployeesApplication.class)
public class EmployeesApplicationTests {

	/*
	 * @Test public void contextLoads() { }
	 */

	private MockMvc mockMvc;

	@Autowired
	EmployeesController employeesController;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(employeesController).build();
	}

	@Test
	public void testEmployeeWithId() throws Exception {
		/* @formatter:off */
		mockMvc.perform(get("/employees/10001"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().encoding("UTF-8"))
				.andExpect(jsonPath("$.employeeNumber").value(equalTo(10001)))
				.andExpect(jsonPath("$.birthDate").value(equalTo("1953-09-02")))
				.andExpect(jsonPath("$.firstName").value(equalTo("Georgi")))
				.andExpect(jsonPath("$.lastName").value(equalTo("Facello")))
				.andExpect(jsonPath("$.gender").value(equalTo("M")))
				.andExpect(jsonPath("$.hireDate").value(equalTo("1986-06-26")));
		/* @formatter:on */
	}
}
