package com.bjss.william.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    public static String formatLocation(String requestURL, String id) {
        return String.format("%s/%s", requestURL, id);
    }
}
