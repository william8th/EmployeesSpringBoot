package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Embeddable
public class DepartmentEmployeePrimaryKey implements Serializable {

    @Column(name = "emp_no", insertable = false, updatable = false)
    private int employeeNumber;

    @Column(name = "dept_no", insertable = false, updatable = false)
    @JsonIgnore
    private String departmentNumber;

    public DepartmentEmployeePrimaryKey() {
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEmployeePrimaryKey that = (DepartmentEmployeePrimaryKey) o;

        if (employeeNumber != that.employeeNumber) return false;
        if (departmentNumber != null ? !departmentNumber.equals(that.departmentNumber) : that.departmentNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeNumber;
        result = 31 * result + (departmentNumber != null ? departmentNumber.hashCode() : 0);
        return result;
    }
}
