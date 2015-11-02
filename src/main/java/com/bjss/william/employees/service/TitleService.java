package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
public class TitleService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Title> getTitlesByEmployeeNumber(int employeeNumber) {
        Employee employee = employeeRepository.findOne(employeeNumber);
        return employee.getTitles();
    }
}
