package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.TitlesDao;
import com.bjss.william.employees.model.Employee;
import com.bjss.william.employees.model.Title;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@Transactional
public abstract class AbstractTitlesDao implements TitlesDao {

    @PersistenceContext
    EntityManager entityManager;

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Title> getTitlesByEmployeeNumber(int employeeNumber) {
        Session session = getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, employeeNumber);
        return employee.getTitles();
    }
}
