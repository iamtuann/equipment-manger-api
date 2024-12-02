package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {
    private Long id;
    private String code;
    private EquipmentTypeDto type;
    private Integer status;
    private Date receiveDate;
    private Date warrantyDate;
    private Date createdAt;
    private Date updatedAt;
    private DepartmentDto department;
    private StorageDto storage;

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.code = equipment.getCode();
        this.type = new EquipmentTypeDto(equipment.getType());
        this.status = equipment.getStatus();
        this.receiveDate = equipment.getReceiveDate();
        this.warrantyDate = equipment.getWarrantyDate();
        this.createdAt = equipment.getCreatedAt();
        this.updatedAt = equipment.getUpdatedAt();
        this.department = equipment.getDepartment() != null ? new DepartmentDto(equipment.getDepartment()) : null;
        this.storage = equipment.getStorage() != null ? new StorageDto(equipment.getStorage()) : null;
    }
}
