package com.vn.equipment_manager.controller;

import com.vn.equipment_manager.model.DepartmentDto;
import com.vn.equipment_manager.model.request.DepartmentRequest;
import com.vn.equipment_manager.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> response = departmentService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable Long id) {
        DepartmentDto response = departmentService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody DepartmentRequest request) {
        departmentService.create(request);
        return ResponseEntity.ok("Create Department successfully!");
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DepartmentRequest request) {
        departmentService.update(id, request);
        return ResponseEntity.ok("Update Department successfully!");
    }
}
