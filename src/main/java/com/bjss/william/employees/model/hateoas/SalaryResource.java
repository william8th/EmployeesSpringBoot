package com.bjss.william.employees.model.hateoas;

import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.controller.SalariesController;
import com.bjss.william.employees.model.Salary;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 03/11/15.
 */
public class SalaryResource extends GenericResource {
    private final Salary salary;

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
}
