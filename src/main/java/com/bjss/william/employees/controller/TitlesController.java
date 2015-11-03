package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
import com.bjss.william.employees.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@RestController
@RequestMapping("/titles")
public class TitlesController {

    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Title>> getTitlesById(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            return new ResponseEntity<>(titleService.getTitlesByEmployeeNumber(employeeNumber), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
