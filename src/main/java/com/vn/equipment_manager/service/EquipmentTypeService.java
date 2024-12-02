package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.EquipmentTypeDto;
import com.vn.equipment_manager.model.EquipmentTypeStatistic;
import com.vn.equipment_manager.model.request.EquipmentTypeRequest;

import java.util.List;

public interface EquipmentTypeService {
    List<EquipmentTypeStatistic> getAll();

    EquipmentTypeDto getById(long id);

    void create(EquipmentTypeRequest request);

    void update(long id, EquipmentTypeRequest request);
}
