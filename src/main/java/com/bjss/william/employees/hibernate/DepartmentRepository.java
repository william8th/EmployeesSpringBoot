package com.bjss.william.employees.hibernate;

import com.bjss.william.employees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Transactional
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
