package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "equipmentTypeRepository")
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
}
