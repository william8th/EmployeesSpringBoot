package com.bjss.william.employees.controller;

import com.bjss.william.employees.dao.DepartmentsDao;
import com.bjss.william.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@RestController
public class DepartmentsController {

    private final static String DEFAULT_RESULT_LIMIT = "10";

    @Autowired
    private DepartmentsDao departmentsDao;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Department>> getDepartments(
            @RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
    ) {

        try {
            int resultLimit = Integer.parseInt(limit);

            if (resultLimit < 1) throw new RuntimeException();

            return new ResponseEntity<>(departmentsDao.getDepartments(resultLimit), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
