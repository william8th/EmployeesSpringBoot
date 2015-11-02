package com.bjss.william.employees.hibernate;

import com.bjss.william.employees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

@Repository
@Transactional
public interface DepartmentRepository extends JpaRepository<Department, String> {
}
