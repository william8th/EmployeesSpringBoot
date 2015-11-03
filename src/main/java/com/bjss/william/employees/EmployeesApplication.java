package com.bjss.william.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class EmployeesApplication {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    public static String formatLocation(String requestURL, String id) {
        return String.format("%s/%s", requestURL, id);
    }

    public static DateFormat getDateFormat() {
        return dateFormat;
    }
}
