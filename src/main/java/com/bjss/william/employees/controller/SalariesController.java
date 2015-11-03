package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Salary;
import com.bjss.william.employees.model.hateoas.SalaryResource;
import com.bjss.william.employees.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@RestController
@RequestMapping("/salaries")
public class SalariesController {

    @Autowired
    SalaryService salaryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<SalaryResource>> getSalariesById(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            List<Salary> salaries = salaryService.getSalariesById(employeeNumber);


            if (salaries == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                List<SalaryResource> salaryResources = salaries
                        .stream()
                        .map(SalaryResource::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(salaryResources, HttpStatus.OK);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
