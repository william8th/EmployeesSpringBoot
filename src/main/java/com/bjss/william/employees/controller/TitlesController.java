package com.bjss.william.employees.controller;

import com.bjss.william.employees.dao.EmployeesDao;
import com.bjss.william.employees.dao.TitlesDao;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@RestController
public class TitlesController {

    @Autowired
    private TitlesDao titlesDao;

    @Autowired
    private EmployeesDao employeesDao;

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Title>> getTitlesById(@PathVariable(value = "id") String id) {
        Employee employee = employeesDao.getEmployeeById(Integer.parseInt(id));
        return new ResponseEntity<List<Title>>(employee.getTitles(), HttpStatus.OK);
//        return new ResponseEntity<List<Title>>(titlesDao.getTitlesByEmployeeNumber(Integer.parseInt(id)), HttpStatus.OK);
    }
}
