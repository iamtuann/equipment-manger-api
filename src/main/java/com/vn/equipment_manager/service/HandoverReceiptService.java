package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.HandoverReceiptDto;
import com.vn.equipment_manager.model.request.HandoverReceiptRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface HandoverReceiptService {
    Page<HandoverReceiptDto> search(String code, Integer status, Date handoverDate, String username, Long departmentId, Date createdAt, Date approvedAt, Pageable pageable);

    HandoverReceiptDto getById(Long id);

    void create(HandoverReceiptRequest request, Long userId);

    void update(Long id, HandoverReceiptRequest request);

    void changeStatus(Long id, Integer status, Long userId);

    void delete(Long id);
}
