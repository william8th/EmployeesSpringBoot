package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.SalariesDao;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Salary;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Transactional
public abstract class AbstractSalariesDao extends GenericDao implements SalariesDao {

    @Override
    public List<Salary> getSalariesById(int employeeNumber) {
        Employee employee = (Employee) getCurrentSession().get(Employee.class, employeeNumber);
        if (employee == null) return null;
        return employee.getSalaries();
    }
}
