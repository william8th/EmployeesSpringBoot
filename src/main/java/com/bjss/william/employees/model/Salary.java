package com.bjss.william.employees.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@Entity
@Table(name = "salaries")
public class Salary {

    @EmbeddedId
    private SalaryPrimaryKey id;

    @Column(name = "salary")
    private int salary;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    public Salary() {
    }

    public SalaryPrimaryKey getId() {
        return id;
    }

    public void setId(SalaryPrimaryKey id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", salary=" + salary +
                ", toDate=" + toDate +
                '}';
    }
}
