package com.bjss.william.employees.dao;

import com.bjss.william.employees.model.Department;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */
public interface DepartmentsDao {
    public List<Department> getDepartments(final int limit);
}
