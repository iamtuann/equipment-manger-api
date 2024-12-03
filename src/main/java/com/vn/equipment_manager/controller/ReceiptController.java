package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.ReceiptDto;
import com.vn.equipment_manager.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/approved")
    public ResponseEntity<List<ReceiptDto>> getApprovedReceipts() {
        List<ReceiptDto> receipts = receiptService.getApprovedReceipts();
        return ResponseEntity.ok(receipts);
    }
}
