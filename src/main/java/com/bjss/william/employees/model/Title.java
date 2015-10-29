package com.bjss.william.employees.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@Entity
@Table(name = "titles")
public class Title {

    @EmbeddedId
    private TitlePrimaryKey id;

    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    public Title() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public TitlePrimaryKey getId() {
        return id;
    }

    public void setId(TitlePrimaryKey id) {
        this.id = id;
    }

}
