package com.bjss.william.employees.dao;

import com.bjss.william.employees.model.Employee;

import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */
public interface EmployeesDao {
    void addEmployee(Employee employee);
    Employee getEmployeeById(final int employeeNumber);
    List<Employee> getEmployees(final int limit);
}
