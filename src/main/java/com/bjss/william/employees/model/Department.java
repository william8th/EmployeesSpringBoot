package com.bjss.william.employees.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "dept_no")
    private String departmentId;

    @Column(name = "dept_name")
    private String departmentName;

    public Department() {
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
