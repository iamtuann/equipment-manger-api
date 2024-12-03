package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.ReceiptDto;

import java.util.List;

public interface ReceiptService {
    List<ReceiptDto> getApprovedReceipts();
}
