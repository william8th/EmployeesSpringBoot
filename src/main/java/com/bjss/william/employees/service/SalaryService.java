package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
@Repository
public class SalaryService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Salary> getSalariesById(final int employeeNumber) {
        Employee employee = employeeRepository.findOne(employeeNumber);
        return employee.getSalaries();
    }
}
