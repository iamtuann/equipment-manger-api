package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.EquipmentDto;
import com.vn.equipment_manager.model.request.EquipmentDepartmentRequest;
import com.vn.equipment_manager.model.request.EquipmentRequest;
import com.vn.equipment_manager.model.request.EquipmentStorageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EquipmentService {

    Page<EquipmentDto> searchEquipment(String code, Integer status,
                                       Long typeId, Long departmentId, Long storageId,
                                       Date receiveDate, Date warrantyDate,
                                       Pageable pageable);

    List<EquipmentDto> getAllInStorage(Long typeId, Long storageId, Integer status);

    EquipmentDto getById(long id);

    void create(EquipmentRequest request);

    void update(Long id, EquipmentRequest request);

    void delete(long id);

    void addToDepartment(EquipmentDepartmentRequest request);

    void addToStorage(EquipmentStorageRequest request);
}
