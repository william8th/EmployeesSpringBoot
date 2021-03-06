package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Entity
@IdClass(DepartmentManagerPrimaryKey.class)
@Table(name = "dept_manager")
public class DepartmentManager {

    @Id
    @JsonIgnore
    private String departmentNumber;

    @Id
    private int employeeNumber;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    @JsonIgnore
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnore
    private Department department;

    public DepartmentManager() {
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
