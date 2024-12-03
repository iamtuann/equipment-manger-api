package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.HandoverReceiptDto;
import com.vn.equipment_manager.model.request.HandoverReceiptRequest;
import com.vn.equipment_manager.security.UserDetailsImpl;
import com.vn.equipment_manager.service.HandoverReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("api/handover-receipts")
public class HandoverReceiptController {
    private final HandoverReceiptService handoverReceiptService;

    @GetMapping("")
    public ResponseEntity<Page<HandoverReceiptDto>> search(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "handoverDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date handoverDate,
            @RequestParam(value = "createdBy", required = false) String username,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "createdAt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date createdAt,
            @RequestParam(value = "approvedAt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date approvedAt,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "20", required = false) int pageSize,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "orderBy", required = false) String orderBy
    ) {
        Sort sort;
        Pageable pageable;
        if (key != null && !key.isEmpty()) {
            if (orderBy.equals("asc")) {
                sort = Sort.by(key).ascending();
            } else {
                sort = Sort.by(key).descending();
            }
        } else {
            sort = Sort.by("createdAt").ascending();
        }
        pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<HandoverReceiptDto> response = handoverReceiptService.search(code, status, handoverDate, username, departmentId, createdAt, approvedAt, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<HandoverReceiptDto> getById(@PathVariable Long id) {
        HandoverReceiptDto response = handoverReceiptService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody HandoverReceiptRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        handoverReceiptService.create(request, userDetails.getId());
        return ResponseEntity.ok("Create HandoverReceipt successfully!");
    }

    @PutMapping("{id}/change-status")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Integer status = Integer.valueOf(request.get("status"));
        handoverReceiptService.changeStatus(id, status, userDetails.getId());
        return ResponseEntity.ok("Approved HandoverReceipt successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        handoverReceiptService.delete(id);
        return ResponseEntity.ok("Delete HandoverReceipt successfully!");
    }
}
