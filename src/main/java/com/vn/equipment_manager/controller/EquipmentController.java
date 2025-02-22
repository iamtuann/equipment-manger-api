package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.EquipmentDto;
import com.vn.equipment_manager.model.request.EquipmentDepartmentRequest;
import com.vn.equipment_manager.model.request.EquipmentRequest;
import com.vn.equipment_manager.model.request.EquipmentStorageRequest;
import com.vn.equipment_manager.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/equipments")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("")
    public ResponseEntity<Page<EquipmentDto>> search(@RequestParam(value = "code", required = false) String code,
                                    @RequestParam(value = "status", required = false) Integer status,
                                    @RequestParam(value = "typeId", required = false) Long typeId,
                                    @RequestParam(value = "departmentId", required = false) Long departmentId,
                                    @RequestParam(value = "storageId", required = false) Long storageId,
                                    @RequestParam(value = "receiveDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date receiveDate,
                                    @RequestParam(value = "warrantyDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date warrantyDate,
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
            sort = Sort.unsorted().ascending();
        }
        pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<EquipmentDto> equipment = equipmentService.searchEquipment(code, status, typeId, departmentId, storageId, receiveDate, warrantyDate, pageable);
        return ResponseEntity.ok(equipment);
    }

    @GetMapping("all/in-storage")
    public ResponseEntity<List<EquipmentDto>> getAllInStorage(@RequestParam(value = "status", required = false) Integer status,
                                                        @RequestParam(value = "typeId", required = false) Long typeId,
                                                        @RequestParam(value = "storageId", required = false) Long storageId) {
        List<EquipmentDto> response = equipmentService.getAllInStorage(typeId, storageId, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<EquipmentDto> getById(@PathVariable Long id) {
        EquipmentDto response = equipmentService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody EquipmentRequest request) {
        equipmentService.create(request);
        return ResponseEntity.ok("Create Equipment successfully!");
    }

    @PostMapping("add-to-department")
    public ResponseEntity<?> addToDepartment(@RequestBody EquipmentDepartmentRequest request) {
        equipmentService.addToDepartment(request);
        return ResponseEntity.ok("Add Equipment to Department successfully!");
    }

    @PostMapping("add-to-storage")
    public ResponseEntity<?> addToStorage(@RequestBody EquipmentStorageRequest request) {
        equipmentService.addToStorage(request);
        return ResponseEntity.ok("Add Equipment to Department successfully!");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EquipmentRequest request) {
        equipmentService.update(id, request);
        return ResponseEntity.ok("Update Equipment successfully!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.ok("Delete Equipment successfully!");
    }
}
