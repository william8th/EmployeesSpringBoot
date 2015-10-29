package com.bjss.william.employees.dao.hibernate;

import com.bjss.william.employees.dao.EmployeesDao;
import com.bjss.william.employees.model.Employee;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractEmployeesDao extends GenericDao implements EmployeesDao {

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
