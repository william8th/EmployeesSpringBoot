package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeesController {

    // Using String because parameter needs to default to String value
    private static final String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
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


    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") String id) {
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(id));

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }


    @RequestMapping(
            value = "/employees",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<EmployeeCreated> addEmployee(
            HttpServletRequest httpServletRequest,
            @RequestBody Employee employee
    ) {
        employeeService.addEmployee(employee);

        if (employee.getEmployeeNumber() == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String newLocation = String.format("%s/%d", httpServletRequest.getRequestURL(), employee.getEmployeeNumber());
        try {
            URI uri = new URI(newLocation);
            HttpHeaders httpResponseHeaders = new HttpHeaders();
            httpResponseHeaders.setLocation(uri);

            return new ResponseEntity<>(new EmployeeCreated(employee.getEmployeeNumber(), newLocation), httpResponseHeaders, HttpStatus.CREATED);
        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}

class EmployeeCreated  {
    private int employeedNumber;
    private String location;

    public EmployeeCreated(int employeedNumber, String location) {
        this.employeedNumber = employeedNumber;
        this.location = location;
    }

    public int getEmployeedNumber() {
        return employeedNumber;
    }

    public String getLocation() {
        return location;
    }
}
