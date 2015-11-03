package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
public class SalaryService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Salary> getSalariesByEmployeeId(final int employeeNumber) throws NoSuchElementException {
        Employee employee = employeeRepository.findOne(employeeNumber);
        return employee.getSalaries();
    }

    public Salary getSalaryByEmployeeIdAndFromDate(final int employeeNumber, final Date fromDate) {
        Employee employee = employeeRepository.findOne(employeeNumber);
        Optional<Salary> salary = employee.getSalaries()
                .stream()
                .filter(x -> x.getFromDate().equals(fromDate))
                .findFirst();

        if (salary.isPresent()) {
            return salary.get();
        } else {
            throw new NoSuchElementException("Salary not found");
        }
    }
}
