package com.bjss.william.employees.model.hateoas;

import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.controller.SalariesController;
import com.bjss.william.employees.model.Salary;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 03/11/15.
 */
public class SalaryResource extends ResourceSupport {
    private final Salary salary;

    @JsonIgnore
    private final URI uri;

    public SalaryResource(Salary salary) {
        this.salary = salary;

        ControllerLinkBuilder selfLinkBuilder = linkTo(SalariesController.class)
                .slash(salary.getEmployeeNumber())
                .slash(salary.getFromDate());
        this.uri = selfLinkBuilder.toUri();

        this.add(selfLinkBuilder.withRel("self"));
        this.add(linkTo(EmployeesController.class).slash(salary.getEmployeeNumber()).withRel("employee"));
    }

    public Salary getSalary() {
        return salary;
    }

    public URI getUri() {
        return uri;
    }
}
