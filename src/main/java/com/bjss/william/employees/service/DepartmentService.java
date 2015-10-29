package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.DepartmentRepository;
import com.bjss.william.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
@Repository
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return departmentRepository.findAll(resultLimit).getContent();
    }
}
