package com.bjss.william.employees.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

public class SalaryPrimaryKey implements Serializable {

    @Column(name = "emp_no", insertable = false, updatable = false)
    protected int employeeNumber;

    @Column(name = "from_date")
    protected Date fromDate;

    public SalaryPrimaryKey() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalaryPrimaryKey that = (SalaryPrimaryKey) o;

        if (employeeNumber != that.employeeNumber) return false;
        return fromDate.equals(that.fromDate);

    }

    @Override
    public int hashCode() {
        int result = employeeNumber;
        result = 31 * result + fromDate.hashCode();
        return result;
    }
}
