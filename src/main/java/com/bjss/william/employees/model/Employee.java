package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore lazy-loaded properties
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    private int employeeNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Title> titles;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<DepartmentManager> departmentManagers;

    public Employee() {
        super();
    }

    public List<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salary> salaries) {
        this.salaries = salaries;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public List<DepartmentManager> getDepartmentManagers() {
        return departmentManagers;
    }

    public void setDepartmentManagers(List<DepartmentManager> departmentManagers) {
        this.departmentManagers = departmentManagers;
    }

}
