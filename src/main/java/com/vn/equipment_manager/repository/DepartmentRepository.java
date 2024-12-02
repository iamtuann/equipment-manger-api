package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentById(long id);
}
