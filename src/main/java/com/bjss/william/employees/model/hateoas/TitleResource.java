package com.bjss.william.employees.model.hateoas;

import com.bjss.william.employees.controller.EmployeesController;
import com.bjss.william.employees.controller.TitlesController;
import com.bjss.william.employees.model.Title;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by William Heng(dev) on 03/11/15.
 */
public class TitleResource extends GenericResource {
    private final Title title;

    public TitleResource(Title title) {
        this.title = title;

        ControllerLinkBuilder selfLinkBuilder = linkTo(TitlesController.class)
                .slash(title.getEmployeeNumber())
                .slash(title.getTitle())
                .slash(title.getFromDate());
        this.uri = selfLinkBuilder.toUri();

        this.add(selfLinkBuilder.withSelfRel());  // Self
        this.add(linkTo(EmployeesController.class).slash(title.getEmployeeNumber()).withRel("employee"));  // Employee
    }

    public Title getTitle() {
        return title;
    }
}
