package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Embeddable
public class DepartmentManagerPrimaryKey implements Serializable {

    @Column(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnore
    private String departmentNumber;

    @Column(name = "emp_no", insertable = false, updatable = false)
    private int employeeNumber;

    public DepartmentManagerPrimaryKey() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentManagerPrimaryKey that = (DepartmentManagerPrimaryKey) o;

        if (employeeNumber != that.employeeNumber) return false;
        return !(departmentNumber != null ? !departmentNumber.equals(that.departmentNumber) : that.departmentNumber != null);

    }

    @Override
    public int hashCode() {
        int result = departmentNumber != null ? departmentNumber.hashCode() : 0;
        result = 31 * result + employeeNumber;
        return result;
    }
}
