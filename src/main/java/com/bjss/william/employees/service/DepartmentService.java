package com.bjss.william.employees.service;

import com.bjss.william.employees.hibernate.DepartmentRepository;
import com.bjss.william.employees.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Service
public class DepartmentService {

    private static final String DEPARTMENT_ID_PREFIX = "d";

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments(int limit) {
        Pageable resultLimit = new PageRequest(0, limit);
        return departmentRepository.findAll(resultLimit).getContent();
    }

    public Department getDepartmentById(String id) throws NoSuchElementException {
        Department department = departmentRepository.findOne(id);
        if (department == null) throw new NoSuchElementException("Department not found");
        return department;
    }

    @Transactional
    public Department addDepartment(Department department) {
        Pageable bottomMost = new PageRequest(0, 1, Sort.Direction.DESC, "departmentId");
        List<Department> departments = departmentRepository.findAll(bottomMost).getContent();
        Department lastDepartment = departments.get(0);
        String oldDepartmentId = lastDepartment.getDepartmentId().replace(DEPARTMENT_ID_PREFIX, "");  // Remove prefix

        try {
            int oldId = Integer.parseInt(oldDepartmentId);
            int newId = oldId + 1;
            String newDepartmentId = String.format("%s%03d", DEPARTMENT_ID_PREFIX, newId);
            department.setDepartmentId(newDepartmentId);
            return departmentRepository.save(department);
        } catch (NumberFormatException e) {
            // Problem in database configuration
            e.printStackTrace();
            return null;
        }
    }
}
