package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.Department;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.DepartmentDto;
import com.vn.equipment_manager.model.request.DepartmentRequest;
import com.vn.equipment_manager.repository.DepartmentRepository;
import com.vn.equipment_manager.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(DepartmentDto::new).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getById(long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return new DepartmentDto(department);
    }

    @Override
    public void create(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        departmentRepository.save(department);
    }

    @Override
    public void update(long id, DepartmentRequest request) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        department.setName(request.getName());
        departmentRepository.save(department);
    }
}
