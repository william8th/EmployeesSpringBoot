package com.bjss.william.employees.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Entity
@Table(name = "dept_manager")
public class DepartmentManager {

    @EmbeddedId
    private DepartmentManagerPrimaryKey id;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_no", insertable = false, updatable = false)
    private Department department;

    public DepartmentManager() {
    }

    public DepartmentManagerPrimaryKey getId() {
        return id;
    }

    public void setId(DepartmentManagerPrimaryKey id) {
        this.id = id;
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
