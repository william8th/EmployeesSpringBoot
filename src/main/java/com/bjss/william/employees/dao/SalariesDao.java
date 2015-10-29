package com.bjss.william.employees.dao;

import com.bjss.william.employees.model.Salary;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

public interface SalariesDao {
    public List<Salary> getSalariesById(final int employeeNumber);
}
