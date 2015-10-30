package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Entity
@Table(name = "departments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore lazy-loaded properties
public class Department {
    @Id
    @Column(name = "dept_no")
    private String departmentId;

    @Column(name = "dept_name")
    private String departmentName;

    @OneToMany
    @JsonIgnore
    private List<DepartmentManager> departmentManagers;

    @OneToMany
    @JsonIgnore
    private List<DepartmentEmployee> departmentEmployees;

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

    public List<DepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(List<DepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

    public List<DepartmentEmployee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(List<DepartmentEmployee> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }
}
