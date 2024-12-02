package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.ImportReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "importReceiptRepository")
public interface ImportReceiptRepository extends JpaRepository<ImportReceipt, Long> {
    List<ImportReceipt> findAllByStatus(Integer status);
}
