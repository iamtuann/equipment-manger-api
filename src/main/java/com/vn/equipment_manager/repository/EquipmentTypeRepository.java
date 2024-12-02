package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.EquipmentType;
import com.vn.equipment_manager.model.EquipmentTypeStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "equipmentTypeRepository")
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
    EquipmentType findEquipmentTypeById(long id);

    @Query(value = "SELECT new com.vn.equipment_manager.model.EquipmentTypeStatistic(et.id, et.name, et.price, " +
            "COUNT(CASE WHEN e.status = 1 THEN 1 ELSE NULL END), " +
            "COUNT(CASE WHEN e.status = 0 THEN 1 ELSE NULL END), " +
            "COUNT(CASE WHEN e.department.id IS NOT NULL THEN 1 ELSE NULL END)) " +
            "FROM Equipment e " +
            "JOIN EquipmentType et ON e.type.id = et.id " +
            "GROUP BY et.id, et.name, et.price")
    List<EquipmentTypeStatistic> getTypeStatistic();
}
