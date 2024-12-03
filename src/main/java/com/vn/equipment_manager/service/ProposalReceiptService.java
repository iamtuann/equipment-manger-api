package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.ProposalReceiptDto;
import com.vn.equipment_manager.model.request.ProposalReceiptRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ProposalReceiptService {
    Page<ProposalReceiptDto> search(String code, Integer status, Date proposalDate, String username, Long departmentId, Date createdAt, Date approvedAt, Pageable pageable);

    ProposalReceiptDto getById(Long id);

    void create(ProposalReceiptRequest request, Long userId);

    void update(Long id, ProposalReceiptRequest request);

    void changeStatus(Long id, Integer status, Long userId);

    void delete(Long id);
}
