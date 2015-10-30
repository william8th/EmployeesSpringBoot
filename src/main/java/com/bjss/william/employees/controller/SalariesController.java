package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Salary;
import com.bjss.william.employees.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@RestController
public class SalariesController {

    @Autowired
    SalaryService salaryService;

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Salary>> getSalariesById(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            List<Salary> salaries = salaryService.getSalariesById(employeeNumber);

            if (salaries == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(salaries, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
