package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.model.ReceiptDto;
import com.vn.equipment_manager.repository.ReceiptRepository;
import com.vn.equipment_manager.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    private final ReceiptRepository receiptRepository;

    @Override
    public List<ReceiptDto> getApprovedReceipts() {
        List<Object[]> rawData = receiptRepository.findApprovedReceipts();
        List<ReceiptDto> receipts = new ArrayList<>();

        for (Object[] record : rawData) {
            ReceiptDto dto = new ReceiptDto();
            dto.setId(((Number) record[0]).longValue());
            dto.setReceiptType((String) record[1]);
            dto.setReceiptCode((String) record[2]);
            dto.setApprovedAt(((Date) record[3]));
            receipts.add(dto);
        }

        return receipts;
    }
}
