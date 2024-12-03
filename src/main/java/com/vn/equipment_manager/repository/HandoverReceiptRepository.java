package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.HandoverReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository(value = "handoverReceiptRepository")
public interface HandoverReceiptRepository extends JpaRepository<HandoverReceipt, Long> {

    boolean existsByCode(String code);

    @Query(value = "SELECT h FROM HandoverReceipt h " +
            "WHERE (:code IS NULL OR :code = '' OR (h.code LIKE CONCAT('%', :code, '%'))) " +
            "AND (:status IS NULL OR h.status = :status) " +
            "AND (:handoverDate IS NULL OR h.handoverDate <= :handoverDate) " +
            "AND (:username IS NULL OR :username = '' OR h.createdBy.username LIKE CONCAT('%', :username, '%') OR h.createdBy.email LIKE CONCAT('%', :username, '%'))" +
            "AND (:createdAt IS NULL OR h.createdAt <= :createdAt) " +
            "AND (:approvedAt IS NULL OR h.approvedAt <= :approvedAt) " +
            "AND (:departmentId IS NULL OR h.department.id = :departmentId) ")
    Page<HandoverReceipt> search(String code,
                               Integer status,
                               Date handoverDate,
                               String username,
                               Long departmentId,
                               Date createdAt,
                               Date approvedAt,
                               Pageable pageable
    );
}
