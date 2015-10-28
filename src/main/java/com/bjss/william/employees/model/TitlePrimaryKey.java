package com.bjss.william.employees.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@Embeddable
public class TitlePrimaryKey implements Serializable {
    @Column(name = "emp_no")
    protected int employeeNumber;

    @Column(name = "title")
    protected String title;

    @Column(name = "from_date")
    protected Date fromDate;

    public TitlePrimaryKey() {
    }

    public TitlePrimaryKey(int employeeNumber, String title, Date fromDate) {
        this.employeeNumber = employeeNumber;
        this.title = title;
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TitlePrimaryKey) {
            TitlePrimaryKey tpk = (TitlePrimaryKey) obj;
            boolean employeeNumberEquals = employeeNumber == tpk.employeeNumber;
            boolean titleEquals = title.equals(tpk.title);
            boolean fromDateEquals = fromDate.equals(tpk.fromDate);
            return employeeNumberEquals && titleEquals && fromDateEquals;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int result = employeeNumber;
        result = 31 * result + title.hashCode();
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        return result;
    }
}