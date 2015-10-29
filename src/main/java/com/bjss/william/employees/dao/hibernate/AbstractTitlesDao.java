package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.TitlesDao;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@Transactional
public abstract class AbstractTitlesDao extends GenericDao implements TitlesDao {

    @Override
    public List<Title> getTitlesByEmployeeNumber(int employeeNumber) {
        Session session = getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, employeeNumber);
        return employee.getTitles();
    }

}
