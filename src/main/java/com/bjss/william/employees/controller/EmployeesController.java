package com.bjss.william.employees.controller;

import com.bjss.william.employees.dao.EmployeesDao;
import com.bjss.william.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {

	// Using String because parameter needs to default to String value
	private static final String DEFAULT_RESULT_LIMIT = "10";
	
	@Autowired
	private EmployeesDao employeesDao;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<Employee>> getEmployees(
			@RequestParam(defaultValue = DEFAULT_RESULT_LIMIT, required = false) String limit
	) {
		try {
			int resultLimit = Integer.parseInt(limit);
			return new ResponseEntity<List<Employee>>(employeesDao.getEmployees(resultLimit),HttpStatus.OK);
		} catch (NumberFormatException e) {
			// Not a valid number
			// Return error
			return new ResponseEntity<List<Employee>>(HttpStatus.BAD_REQUEST);
		}
	}


    @RequestMapping(
            value = "/employees",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Employee> addEmployee(
            @RequestBody Employee employee
    ) {
        employeesDao.addEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }


	@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") String id) {
		Employee employee = employeesDao.getEmployeeById(Integer.parseInt(id));

        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
	}
}
