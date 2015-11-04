package com.bjss.william.employees.model.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.bjss.william.employees.controller.DepartmentsController;
import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.model.Department;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

/**
 * Created by William Heng(dev) on 03/11/15.
 */

public class DepartmentResource extends GenericResource {
    private Department department;

    public DepartmentResource(Department department) {
        this.department = department;

        ControllerLinkBuilder selfLinkBuilder = linkTo(DepartmentsController.class).slash(department.getDepartmentId());
        this.uri = selfLinkBuilder.toUri();

        this.add(selfLinkBuilder.withSelfRel());  // Self
        this.add(linkTo(methodOn(DepartmentsController.class).getDepartmentEmployeesById(department.getDepartmentId()))
                .withRel("departmentEmployees"));  // Department employees
    }

    public Department getDepartment() {
        return department;
    }
}
