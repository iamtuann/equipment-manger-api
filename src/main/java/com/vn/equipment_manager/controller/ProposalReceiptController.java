package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.ProposalReceiptDto;
import com.vn.equipment_manager.model.request.ProposalReceiptRequest;
import com.vn.equipment_manager.security.UserDetailsImpl;
import com.vn.equipment_manager.service.ProposalReceiptService;
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
@RequestMapping("api/proposal-receipts")
public class ProposalReceiptController {
    private final ProposalReceiptService proposalReceiptService;

    @GetMapping("")
    public ResponseEntity<Page<ProposalReceiptDto>> search(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "proposalDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date proposalDate,
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
        Page<ProposalReceiptDto> response = proposalReceiptService.search(code, status, proposalDate, username, departmentId, createdAt, approvedAt, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProposalReceiptDto> getById(@PathVariable Long id) {
        ProposalReceiptDto response = proposalReceiptService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProposalReceiptRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        proposalReceiptService.create(request, userDetails.getId());
        return ResponseEntity.ok("Create ProposalReceipt successfully!");
    }

    @PutMapping("{id}/change-status")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Integer status = Integer.valueOf(request.get("status"));
        proposalReceiptService.changeStatus(id, status, userDetails.getId());
        return ResponseEntity.ok("Approved ProposalReceipt successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        proposalReceiptService.delete(id);
        return ResponseEntity.ok("Delete ProposalReceipt successfully!");
    }
}
