package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Department;
import com.bjss.william.employees.model.DepartmentEmployee;
import com.bjss.william.employees.model.DepartmentManager;
import com.bjss.william.employees.model.hateoas.DepartmentResource;
import com.bjss.william.employees.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    private final static String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DepartmentResource>> getDepartments(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {

        try {
            int resultLimit = Integer.parseInt(limit);
            if (resultLimit < 1) throw new IllegalArgumentException("Invalid limit argument");

            List<DepartmentResource> departmentResources = departmentService.getDepartments(resultLimit)
                    .stream()
                    .map(DepartmentResource::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(departmentResources, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<DepartmentResource> addDepartment(
            HttpServletRequest httpServletRequest,
            @RequestBody Department department
    ) {
        Department newDepartment = departmentService.addDepartment(department);

        if (newDepartment == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        DepartmentResource newDepartmentResource = new DepartmentResource(newDepartment);
        URI uri = newDepartmentResource.getUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<>(
                newDepartmentResource,
                httpHeaders,
                HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DepartmentResource> getDepartmentById(@PathVariable String id) {

        try {
            Department department = departmentService.getDepartmentById(id);

            DepartmentResource departmentResource = new DepartmentResource(department);
            departmentResource.add(linkTo(DepartmentsController.class).withRel("departments"));

            return new ResponseEntity<>(departmentResource, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/{id}/employees", method = RequestMethod.GET)
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


    @RequestMapping(value = "/{id}/managers", method = RequestMethod.GET)
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
