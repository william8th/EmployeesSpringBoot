package com.bjss.william.employees.controller;

import com.bjss.william.employees.EmployeesApplication;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    // Using String because parameter needs to default to String value
    private static final String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {
        try {
            int resultLimit = Integer.parseInt(limit);
            return new ResponseEntity<>(employeeService.getEmployees(resultLimit), HttpStatus.OK);
        } catch (NumberFormatException e) {
            // Not a valid number
            // Return error
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeCreated> addEmployee(
            HttpServletRequest httpServletRequest,
            @RequestBody Employee employee
    ) {
        Employee newEmployee = employeeService.addEmployee(employee);

        String newLocation = EmployeesApplication.formatLocation(
                httpServletRequest.getRequestURL().toString(),
                Integer.toString(newEmployee.getEmployeeNumber()));
        try {
            URI uri = new URI(newLocation);
            HttpHeaders httpResponseHeaders = new HttpHeaders();
            httpResponseHeaders.setLocation(uri);

            return new ResponseEntity<>(
                    new EmployeeCreated(newEmployee.getEmployeeNumber(), newLocation),
                    httpResponseHeaders,
                    HttpStatus.CREATED);

        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(id));

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }
}

class EmployeeCreated {
    private int employeeNumber;
    private String location;

    public EmployeeCreated(int employeeNumber, String location) {
        this.employeeNumber = employeeNumber;
        this.location = location;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getLocation() {
        return location;
    }
}
