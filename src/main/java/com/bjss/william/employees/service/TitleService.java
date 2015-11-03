package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.EmployeeRepository;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
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
public class TitleService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Title> getTitlesByEmployeeNumber(final int employeeNumber) throws NoSuchElementException {
        Employee employee = employeeRepository.findOne(employeeNumber);
        if (employee == null) throw new NoSuchElementException("Employee not found");
        return employee.getTitles();
    }

    public Title getTitle(final int employeeNumber, final String titleLiteral, final Date fromDate) {
        Employee employee = employeeRepository.findOne(employeeNumber);
        if (employee == null) throw new NoSuchElementException("Employee not found");

        Optional<Title> title = employee.getTitles()
                .stream()
                .filter(x -> x.getFromDate().equals(fromDate))
                .filter(x -> x.getTitle().equals(titleLiteral))
                .findFirst();

        if (title.isPresent()) {
            return title.get();
        } else {
            throw new NoSuchElementException("Title not found");
        }
    }
}
