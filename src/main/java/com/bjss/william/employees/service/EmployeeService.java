package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
@Repository
public class EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    public Employee getEmployeeById(int employeeNumber) {
        return employeeRepository.findOne(employeeNumber);
    }

    public List<Employee> getEmployees(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return employeeRepository.findAll(resultLimit).getContent();
    }
}
