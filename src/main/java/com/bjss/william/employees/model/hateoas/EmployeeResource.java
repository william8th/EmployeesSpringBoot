package com.bjss.william.employees.model.hateoas;

import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.controller.SalariesController;
import com.bjss.william.employees.controller.TitlesController;
import com.bjss.william.employees.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 02/11/15.
 */
public class EmployeeResource extends ResourceSupport {
    private final Employee employee;

    @JsonIgnore
    private final URI uri;

    public EmployeeResource(Employee employee) {
        int employeeNumber = employee.getEmployeeNumber();
        this.employee = employee;

        ControllerLinkBuilder selfLinkBuilder = linkTo(EmployeesController.class).slash(employeeNumber);
        this.uri = selfLinkBuilder.toUri();

        this.add(selfLinkBuilder.withSelfRel());  // Self
        this.add(linkTo(SalariesController.class).slash(employeeNumber).withRel("salaries"));  // Salaries
        this.add(linkTo(TitlesController.class).slash(employeeNumber).withRel("titles"));  // Titles
    }

    public Employee getEmployee() {
        return employee;
    }

    public URI getUri() {
        return uri;
    }
}
