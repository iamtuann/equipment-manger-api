package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "equipmentRepository")
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsByCode(String code);

    @Query(value = "SELECT e FROM Equipment e " +
            "WHERE e.department.id IS NULL " +
            "AND (:status IS NULL OR e.status = :status) " +
            "AND (:typeId IS NULL OR e.type.id = :typeId) " +
            "AND (e.storage.id IS NOT NULL)" +
            "AND (:storageId IS NULL OR e.storage.id = :storageId)")
    List<Equipment> findAllInStorage(@Param(value = "typeId") Long typeId,
                                     @Param(value = "storageId") Long storageId,
                                     @Param(value = "status") Integer status);

    @Query(value = "SELECT e FROM Equipment e " +
            "WHERE (:code IS NULL OR :code = '' OR (e.code LIKE CONCAT('%', :code, '%'))) " +
            "AND (:status IS NULL OR e.status = :status) " +
            "AND (:typeId IS NULL OR e.type.id = :typeId) " +
            "AND (:departmentId IS NULL OR e.department.id = :departmentId) " +
            "AND (:storageId IS NULL OR e.storage.id = :storageId) " +
            "AND (:receiveDate IS NULL OR e.receiveDate <= :receiveDate) " +
            "AND (:warrantyDate IS NULL OR e.warrantyDate >= :warrantyDate) ")
    Page<Equipment> search(@Param(value = "code") String code,
                           @Param(value = "status") Integer status,
                           @Param(value = "typeId") Long typeId,
                           @Param(value = "departmentId") Long departmentId,
                           @Param(value = "storageId") Long storageId,
                           @Param(value = "receiveDate")Date receiveDate,
                           @Param(value = "warrantyDate") Date warrantyDate,
                           Pageable pageable
                           );
}
