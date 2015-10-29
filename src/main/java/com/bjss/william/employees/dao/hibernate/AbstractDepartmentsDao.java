package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.DepartmentsDao;
import com.bjss.william.employees.model.Department;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */
public class AbstractDepartmentsDao extends GenericDao implements DepartmentsDao {
    @Override
    public List<Department> getDepartments(final int limit) {
        Session session = getCurrentSession();
        List<Department> departments = session.createQuery("from Department").setMaxResults(limit).list();
        return departments;
    }
}