package com.bjss.william.employees.dao;

import com.bjss.william.employees.model.Title;

import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */
public interface TitlesDao {
    public List<Title> getTitlesByEmployeeNumber(final int employeeNumber);
}
