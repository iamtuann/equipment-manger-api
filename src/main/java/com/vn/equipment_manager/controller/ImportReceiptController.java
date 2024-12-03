package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;
import com.vn.equipment_manager.security.UserDetailsImpl;
import com.vn.equipment_manager.service.ImportReceiptService;
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
@RequestMapping("api/import-receipts")
public class ImportReceiptController {
    private final ImportReceiptService importReceiptService;

    @GetMapping("")
    public ResponseEntity<Page<ImportReceiptDto>> search(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "importDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date importDate,
            @RequestParam(value = "createdBy", required = false) String username,
            @RequestParam(value = "createdAt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date createdAt,
            @RequestParam(value = "updatedAt", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date updatedAt,
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
        Page<ImportReceiptDto> response = importReceiptService.search(code, status, importDate, username, createdAt, updatedAt, pageable);
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

    @PutMapping("{id}/change-status")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Integer status = Integer.valueOf(request.get("status"));
        importReceiptService.changeStatus(id, status, userDetails.getId());
        return ResponseEntity.ok("Approved ImportReceipt successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        importReceiptService.delete(id);
        return ResponseEntity.ok("Delete ImportReceipt successfully!");
    }
}
