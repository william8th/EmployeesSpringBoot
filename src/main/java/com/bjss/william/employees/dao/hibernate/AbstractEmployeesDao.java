package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.EmployeesDao;
import com.bjss.william.employees.model.Employee;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractEmployeesDao implements EmployeesDao {

    @PersistenceContext
    private EntityManager entityManager;

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = getCurrentSession();
        session.save(employee);
    }

    @Override
    public Employee getEmployeeById(final int employeeNumber) {
        Session session = getCurrentSession();
        Employee employee = (Employee) session.get(Employee.class, employeeNumber);
        return employee;
    }

    @Override
    public List<Employee> getEmployees(final int limit) {
        Session session = getCurrentSession();
        List<Employee> employees = session.createQuery("from Employee").setMaxResults(limit).list();
        return employees;
    }
}
