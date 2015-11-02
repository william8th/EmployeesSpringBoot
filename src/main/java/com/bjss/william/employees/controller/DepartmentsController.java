package com.bjss.william.employees.controller;

import com.bjss.william.employees.EmployeesApplication;
import com.bjss.william.employees.model.Department;
import com.bjss.william.employees.model.DepartmentEmployee;
import com.bjss.william.employees.model.DepartmentManager;
import com.bjss.william.employees.service.DepartmentService;
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

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@RestController
public class DepartmentsController {

    private final static String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Department>> getDepartments(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {

        try {
            int resultLimit = Integer.parseInt(limit);

            if (resultLimit < 1) throw new RuntimeException();

            return new ResponseEntity<>(departmentService.getDepartments(resultLimit), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(
            value = "/departments",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<DepartmentCreated> addDepartment(
            HttpServletRequest httpServletRequest,
            @RequestBody Department department
    ) {
        Department newDepartment = departmentService.addDepartment(department);

        if (newDepartment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            String newLocation = EmployeesApplication.formatLocation(
                    httpServletRequest.getRequestURL().toString(),
                    newDepartment.getDepartmentId());
            URI uri = new URI(newLocation);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(new DepartmentCreated(
                    newDepartment.getDepartmentId(), newLocation),
                    httpHeaders,
                    HttpStatus.CREATED);
        } catch (URISyntaxException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Department> getDepartmentById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException();

            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/departments/{id}/employees", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DepartmentEmployee>> getDepartmentEmployeesById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException("No department found with ID provided");

            return new ResponseEntity<>(department.getDepartmentEmployees(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/departments/{id}/managers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DepartmentManager>> getDepartmentManagersById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            if (department == null) throw new RuntimeException();

            return new ResponseEntity<>(department.getDepartmentManagers(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}

class DepartmentCreated {
    private String departmentId;
    private String location;

    public DepartmentCreated(String departmentId, String location) {
        this.departmentId = departmentId;
        this.location = location;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getLocation() {
        return location;
    }
}
