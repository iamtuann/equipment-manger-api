package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Department;
import com.vn.equipment_manager.model.DepartmentStatistic;
import com.vn.equipment_manager.model.StorageStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "departmentRepository")
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentById(long id);

    @Query("SELECT new com.vn.equipment_manager.model.DepartmentStatistic(d.id, d.name, " +
            "       COUNT(DISTINCT e.type.id), " +
            "       COUNT(e)) " +
            "FROM Department d " +
            "LEFT JOIN Equipment e ON e.department.id = d.id " +
            "GROUP BY d.id, d.name")
    List<DepartmentStatistic> getDepartmentStatistics();
}
