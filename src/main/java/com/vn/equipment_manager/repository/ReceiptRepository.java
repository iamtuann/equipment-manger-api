package com.vn.equipment_manager.repository;

import com.vn.equipment_manager.entity.ProposalReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "receiptRepository")
public interface ReceiptRepository extends JpaRepository<ProposalReceipt, Long> {

    @Query(value = "SELECT id, 'ProposalReceipt' as receiptType, code AS receiptCode, approved_at " +
            "FROM proposal_receipt WHERE status = 1 " +
            "UNION ALL " +
            "SELECT id, 'ImportReceipt' as receiptType, code AS receiptCode, approved_at " +
            "FROM import_receipt WHERE status = 1 " +
            "UNION ALL " +
            "SELECT id, 'HandoverReceipt' as receiptType, code AS receiptCode, approved_at " +
            "FROM handover_receipt WHERE status = 1 " +
            "ORDER BY approved_at DESC", nativeQuery = true)
    List<Object[]> findApprovedReceipts();
}
