package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.DepartmentDto;
import com.vn.equipment_manager.model.DepartmentStatistic;
import com.vn.equipment_manager.model.request.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentStatistic> getAll();

    DepartmentDto getById(long id);

    void create(DepartmentRequest request);

    void update(long id, DepartmentRequest request);

    void delete(long id);
}
