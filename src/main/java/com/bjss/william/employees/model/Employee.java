package com.bjss.william.employees.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore lazy-loaded properties
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no")
    @NotNull @Digits(integer = 5, fraction = 0)
    private int employeeNumber;

    @Column(name = "birth_date")
    @NotNull
    private Date birthDate;

    @Column(name = "first_name")
    @NotNull @Size(max = 14)
    private String firstName;

    @Column(name = "last_name")
    @NotNull @Size(max = 16)
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;

    @Column(name = "hire_date")
    @NotNull
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
