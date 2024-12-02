package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.StorageDto;
import com.vn.equipment_manager.model.StorageStatistic;
import com.vn.equipment_manager.model.request.StorageRequest;
import com.vn.equipment_manager.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/storages")
public class StorageController {
    private final StorageService storageService;

    @GetMapping("")
    public ResponseEntity<List<StorageStatistic>> getAll() {
        List<StorageStatistic> response = storageService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<StorageDto> getById(@PathVariable Long id) {
        StorageDto response = storageService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody StorageRequest request) {
        storageService.create(request);
        return ResponseEntity.ok("Create Storage successfully!");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StorageRequest request) {
        storageService.update(id, request);
        return ResponseEntity.ok("Update Storage successfully!");
    }
}
