package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;
import com.vn.equipment_manager.security.UserDetailsImpl;
import com.vn.equipment_manager.service.ImportReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/import-receipts")
public class ImportReceiptController {
    private final ImportReceiptService importReceiptService;

    @GetMapping("")
    public ResponseEntity<List<ImportReceiptDto>> getAll(@RequestParam(value = "status", required = false) String statusString) {
        List<ImportReceiptDto> response = new ArrayList<>();
        if (statusString != null) {
            Integer status = switch (statusString) {
                case "pending" -> EReceiptStatus.PENDING.getValue();
                case "approved" -> EReceiptStatus.APPROVED.getValue();
                case "rejected" -> EReceiptStatus.REJECTED.getValue();
                default -> null;
            };
            response = importReceiptService.getAllByStatus(status);
        } else {
            response = importReceiptService.getAll();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ImportReceiptDto> getById(@PathVariable Long id) {
        ImportReceiptDto response = importReceiptService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ImportReceiptRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        importReceiptService.create(request, userDetails.getId());
        return ResponseEntity.ok("Create ImportReceipt successfully!");
    }
}
