package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.ImportReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository(value = "importReceiptRepository")
public interface ImportReceiptRepository extends JpaRepository<ImportReceipt, Long> {
    List<ImportReceipt> findAllByStatus(Integer status);

    boolean existsByCode(String code);

    @Query(value = "SELECT i FROM ImportReceipt i " +
            "WHERE (:code IS NULL OR :code = '' OR (i.code LIKE CONCAT('%', :code, '%'))) " +
            "AND (:status IS NULL OR i.status = :status) " +
            "AND (:importDate IS NULL OR i.importDate <= :importDate) " +
            "AND (:username IS NULL OR :username = '' OR i.createdBy.username LIKE CONCAT('%', :username, '%') OR i.createdBy.email LIKE CONCAT('%', :username, '%'))" +
            "AND (:createdAt IS NULL OR i.createdAt <= :createdAt) " +
            "AND (:updatedAt IS NULL OR i.updatedAt <= :updatedAt) ")
    Page<ImportReceipt> search(String code,
                               Integer status,
                               Date importDate,
                               String username,
                               Date createdAt,
                               Date updatedAt,
                               Pageable pageable
    );
}
