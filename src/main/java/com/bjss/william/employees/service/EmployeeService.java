package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int employeeNumber) throws NoSuchElementException {
        Employee employee = employeeRepository.findOne(employeeNumber);
        if (employee == null) throw new NoSuchElementException("Employee not found");
        return employee;
    }

    public List<Employee> getEmployees(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return employeeRepository.findAll(resultLimit).getContent();
    }
}
