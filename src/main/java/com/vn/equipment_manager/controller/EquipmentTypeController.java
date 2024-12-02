package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.EquipmentTypeDto;
import com.vn.equipment_manager.model.request.EquipmentTypeRequest;
import com.vn.equipment_manager.service.EquipmentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/types")
public class EquipmentTypeController {
    private final EquipmentTypeService equipmentTypeService;

    @GetMapping("")
    public ResponseEntity<List<EquipmentTypeDto>> getAll() {
        List<EquipmentTypeDto> response = equipmentTypeService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<EquipmentTypeDto> getById(@PathVariable Long id) {
        EquipmentTypeDto response = equipmentTypeService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody EquipmentTypeRequest request) {
        equipmentTypeService.create(request);
        return ResponseEntity.ok("Create EquipmentType successfully!");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EquipmentTypeRequest request) {
        equipmentTypeService.update(id, request);
        return ResponseEntity.ok("Update EquipmentType successfully!");
    }
}
