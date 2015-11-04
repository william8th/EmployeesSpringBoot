package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.hateoas.EmployeeResource;
import com.bjss.william.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    // Using String because parameter needs to default to String value
    private static final String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmployeeResource>> getEmployees(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) Integer limit
    ) {
        List<EmployeeResource> employeeResources = employeeService.getEmployees(limit)
                .stream()
                .map(EmployeeResource::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(employeeResources, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EmployeeResource> addEmployee(
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody Employee employee
    ) {
        Employee newEmployee = employeeService.addEmployee(employee);
        EmployeeResource newEmployeeResource = new EmployeeResource(newEmployee);

        HttpHeaders httpResponseHeaders = new HttpHeaders();
        httpResponseHeaders.setLocation(newEmployeeResource.getUri());

        return new ResponseEntity<>(
                newEmployeeResource,
                httpResponseHeaders,
                HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EmployeeResource> getEmployeeById(@PathVariable(value = "id") int id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(new EmployeeResource(employee), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
