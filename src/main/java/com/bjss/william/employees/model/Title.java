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
    private TitlePrimaryKey titlePrimaryKey;

    @Column(name = "to_date")
    private Date toDate;

    public Title() {
    }

    public int getEmployeeNumber() {
        return titlePrimaryKey.employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        titlePrimaryKey.employeeNumber = employeeNumber;
    }

    public String getTitle() {
        return titlePrimaryKey.title;
    }

    public void setTitle(String title) {
        titlePrimaryKey.title = title;
    }

    public Date getFromDate() {
        return titlePrimaryKey.fromDate;
    }

    public void setFromDate(Date fromDate) {
        titlePrimaryKey.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Title{" +
                "employeeNumber=" + titlePrimaryKey.employeeNumber +
                ", title='" + titlePrimaryKey.title + '\'' +
                ", fromDate=" + titlePrimaryKey.fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
