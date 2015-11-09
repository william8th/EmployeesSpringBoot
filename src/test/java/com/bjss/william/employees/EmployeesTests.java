package com.bjss.william.employees;

import com.bjss.william.employees.controller.EmployeesController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmployeesApplication.class)
@WebAppConfiguration
public class EmployeesTests {

    private MockMvc mockMvc;

    private static final String EMPLOYEES_10_RESULTS = "src/test/java/com/bjss/william/employees/resources/employees-10-results.json";
    private static final String EMPLOYEES_20_RESULTS = "src/test/java/com/bjss/william/employees/resources/employees-20-results.json";

    @Autowired
    EmployeesController employeesController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
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

    @Test
    public void testGetListOfEmployees() throws Exception {
        MvcResult result = mockMvc.perform(get("/employees")).andReturn();
        String actualJsonString = result.getResponse().getContentAsString();

        Path path = Paths.get(EMPLOYEES_10_RESULTS);
        String expectedJsonString = Files.lines(path).collect(Collectors.joining());

        actualJsonString = actualJsonString.replaceAll("\\s+", "");
        expectedJsonString = expectedJsonString.replaceAll("\\s+", "");

        Assert.assertEquals(expectedJsonString, actualJsonString);
    }

    @Test
    public void testGetListOfTwentyEmployees() throws Exception {
        MvcResult result = mockMvc.perform(get("/employees?limit=20")).andReturn();
        String actualJsonString = result.getResponse().getContentAsString();

        Path path = Paths.get(EMPLOYEES_20_RESULTS);
        String expectedJsonString = Files.lines(path).collect(Collectors.joining());

        actualJsonString = actualJsonString.replaceAll("\\s+", "");
        expectedJsonString = expectedJsonString.replaceAll("\\s+", "");

        Assert.assertEquals(expectedJsonString, actualJsonString);
    }
}
