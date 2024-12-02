package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.EquipmentDto;
import com.vn.equipment_manager.model.request.EquipmentRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface EquipmentService {

    Page<EquipmentDto> searchEquipment(String code, Integer status,
                                       Long typeId, Long departmentId, Long storageId,
                                       Date receiveDate, Date warrantyDate,
                                       Pageable pageable);

    EquipmentDto getById(long id);

    void create(EquipmentRequest request);

    void update(Long id, EquipmentRequest request);

    void delete(long id);
}
