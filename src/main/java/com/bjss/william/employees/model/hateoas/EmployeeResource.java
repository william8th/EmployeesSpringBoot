package com.bjss.william.employees.model.hateoas;

import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.controller.SalariesController;
import com.bjss.william.employees.controller.TitlesController;
import com.bjss.william.employees.model.Employee;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 02/11/15.
 */
public class EmployeeResource extends GenericResource {
    private final Employee employee;

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
}
