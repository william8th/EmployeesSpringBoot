package com.bjss.william.employees.controller;

import com.bjss.william.employees.EmployeesApplication;
import com.bjss.william.employees.model.Salary;
import com.bjss.william.employees.model.hateoas.SalaryResource;
import com.bjss.william.employees.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<List<SalaryResource>> getSalariesByEmployeeId(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            List<Salary> salaries = salaryService.getSalariesByEmployeeId(employeeNumber);


            if (salaries == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                List<SalaryResource> salaryResources = salaries
                        .stream()
                        .map(SalaryResource::new)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(salaryResources, HttpStatus.OK);
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{id}/{date}")
    @ResponseBody
    public ResponseEntity<SalaryResource> getSalaryById(
            @PathVariable(value = "id") String id,
            @PathVariable(value = "date") String date
    ) {
        try {
            int employeeNumber = Integer.parseInt(id);

            DateFormat df = EmployeesApplication.getDateFormat();
            Date parsedDate = df.parse(date);

            Salary salary = salaryService.getSalary(employeeNumber, parsedDate);
            SalaryResource salaryResource = new SalaryResource(salary);

            return new ResponseEntity<>(salaryResource, HttpStatus.FOUND);

        } catch (NumberFormatException | ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
