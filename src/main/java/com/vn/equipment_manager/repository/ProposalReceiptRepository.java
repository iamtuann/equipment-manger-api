package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.ProposalReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository(value = "proposalReceiptRepository")
public interface ProposalReceiptRepository extends JpaRepository<ProposalReceipt, Long> {

    boolean existsByCode(String code);

    @Query(value = "SELECT p FROM ProposalReceipt p " +
            "WHERE (:code IS NULL OR :code = '' OR (p.code LIKE CONCAT('%', :code, '%'))) " +
            "AND (:status IS NULL OR p.status = :status) " +
            "AND (:proposalDate IS NULL OR p.proposalDate <= :proposalDate) " +
            "AND (:username IS NULL OR :username = '' OR p.createdBy.username LIKE CONCAT('%', :username, '%') OR p.createdBy.email LIKE CONCAT('%', :username, '%'))" +
            "AND (:createdAt IS NULL OR p.createdAt <= :createdAt) " +
            "AND (:approvedAt IS NULL OR p.approvedAt <= :approvedAt) " +
            "AND (:departmentId IS NULL OR p.department.id = :departmentId) ")
    Page<ProposalReceipt> search(String code,
                                 Integer status,
                                 Date proposalDate,
                                 String username,
                                 Long departmentId,
                                 Date createdAt,
                                 Date approvedAt,
                                 Pageable pageable
    );
}
