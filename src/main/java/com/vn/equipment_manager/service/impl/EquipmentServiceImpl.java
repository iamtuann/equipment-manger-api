package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.Department;
import com.vn.equipment_manager.entity.Equipment;
import com.vn.equipment_manager.entity.Storage;
import com.vn.equipment_manager.exception.BadRequestException;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.EquipmentDto;
import com.vn.equipment_manager.model.request.EquipmentDepartmentRequest;
import com.vn.equipment_manager.model.request.EquipmentRequest;
import com.vn.equipment_manager.model.request.EquipmentStorageRequest;
import com.vn.equipment_manager.repository.DepartmentRepository;
import com.vn.equipment_manager.repository.EquipmentRepository;
import com.vn.equipment_manager.repository.EquipmentTypeRepository;
import com.vn.equipment_manager.repository.StorageRepository;
import com.vn.equipment_manager.service.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final StorageRepository storageRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<EquipmentDto> searchEquipment(String code, Integer status, Long typeId, Long departmentId, Long storageId, Date receiveDate, Date warrantyDate, Pageable pageable) {
        Page<Equipment> equipmentPage = equipmentRepository.search(code, status, typeId, departmentId, storageId, receiveDate, warrantyDate, pageable);
        List<EquipmentDto> equipments = equipmentPage.stream()
                .map(EquipmentDto::new).toList();
        return new PageImpl<>(equipments, pageable, equipmentPage.getTotalElements());
    }

    @Override
    public List<EquipmentDto> getAllInStorage(Long typeId, Long storageId, Integer status) {
        List<Equipment> equipment = equipmentRepository.findAllInStorage(typeId, storageId, status);
        return equipment.stream()
                .map(EquipmentDto::new).toList();
    }

    @Override
    public EquipmentDto getById(long id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", id));
        return new EquipmentDto(equipment);
    }

    @Override
    public void create(EquipmentRequest request) {
        if (equipmentRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Code", request.getCode(), "Code equipment is existed");
        }
        Equipment equipment = new Equipment();
        equipment.setCode(request.getCode());
        equipment.setStatus(request.getStatus());
        equipment.setReceiveDate(request.getReceiveDate());
        equipment.setWarrantyDate(request.getWarrantyDate());
        equipment.setType(equipmentTypeRepository.findEquipmentTypeById(request.getTypeId()));
        if (request.getStorageId() != null) {
            equipment.setStorage(storageRepository.findStorageById(request.getStorageId()));
        }
        if (request.getDepartmentId() != null) {
            equipment.setDepartment(departmentRepository.findDepartmentById(request.getDepartmentId()));
        }
        if (request.getStorageId() != null) {
            equipment.setStorage(storageRepository.findStorageById(request.getStorageId()));
        }
        equipmentRepository.save(equipment);
    }

    @Override
    public void update(Long id, EquipmentRequest request) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", id));
        equipment.setCode(request.getCode());
        equipment.setStatus(request.getStatus());
        equipment.setReceiveDate(request.getReceiveDate());
        equipment.setWarrantyDate(request.getWarrantyDate());
        if (request.getStorageId() != null) {
            equipment.setStorage(storageRepository.findStorageById(request.getStorageId()));
        } else {
            equipment.setStorage(null);
        }
        if (request.getDepartmentId() != null) {
            equipment.setDepartment(departmentRepository.findDepartmentById(request.getDepartmentId()));
            equipment.setStorage(null);
        } else {
            equipment.setDepartment(null);
        }
        if (request.getStorageId() != null) {
            equipment.setStorage(storageRepository.findStorageById(request.getStorageId()));
            equipment.setDepartment(null);
        } else {
            equipment.setStorage(null);
        }
        equipmentRepository.save(equipment);
    }

    @Override
    public void delete(long id) {
        boolean isExist = equipmentRepository.existsById(id);
        if (isExist) {
            equipmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Equipment", "id", id);
        }
    }

    @Override
    public void addToDepartment(EquipmentDepartmentRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));

        List<Equipment> equipments = equipmentRepository.findAllById(request.getEquipmentIds());
        for (Equipment equipment : equipments) {
            equipment.setStorage(null);
            equipment.setDepartment(department);
        }
        equipmentRepository.saveAll(equipments);
    }

    @Override
    public void addToStorage(EquipmentStorageRequest request) {
        Storage storage = storageRepository.findById(request.getStorageId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getStorageId()));

        List<Equipment> equipments = equipmentRepository.findAllById(request.getEquipmentIds());
        for (Equipment equipment : equipments) {
            equipment.setStorage(storage);
            equipment.setDepartment(null);
        }
        equipmentRepository.saveAll(equipments);
    }
}
