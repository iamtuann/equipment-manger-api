package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.EquipmentType;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.EquipmentTypeDto;
import com.vn.equipment_manager.model.request.EquipmentTypeRequest;
import com.vn.equipment_manager.repository.EquipmentTypeRepository;
import com.vn.equipment_manager.service.EquipmentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;

    @Override
    public List<EquipmentTypeDto> getAll() {
        List<EquipmentType> types = equipmentTypeRepository.findAll();
        return types.stream()
                .map(EquipmentTypeDto::new).collect(Collectors.toList());
    }

    @Override
    public EquipmentTypeDto getById(long id) {
        EquipmentType type = equipmentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment type", "id", id));
        return new EquipmentTypeDto(type);
    }

    @Override
    @Transactional
    public void create(EquipmentTypeRequest request) {
        EquipmentType type = new EquipmentType();
        type.setName(request.getName());
        type.setPrice(request.getPrice());
        equipmentTypeRepository.save(type);
    }

    @Override
    @Transactional
    public void update(long id, EquipmentTypeRequest request) {
        EquipmentType type = equipmentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment type", "id", id));
        type.setName(request.getName());
        type.setPrice(request.getPrice());
        equipmentTypeRepository.save(type);
    }
}
