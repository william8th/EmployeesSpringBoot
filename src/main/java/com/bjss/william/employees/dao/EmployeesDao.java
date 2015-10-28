package com.bjss.william.employees.dao;

import com.bjss.william.employees.model.Employee;

import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */
public interface EmployeesDao {
    public void addEmployee(Employee employee);
    public Employee getEmployeeById(final int employeeNumber);
    public List<Employee> getEmployees(final int limit);
}
