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
    private String equipmentType;
    private Integer status;
    private Date receiveDate;
    private Date warrantyDate;
    private Date createdAt;
    private Date updatedAt;
    private DepartmentDto department;

    public EquipmentDto(Equipment equipment) {
        this.id = equipment.getId();
        this.code = equipment.getCode();
        this.equipmentType = equipment.getType().getName();
        this.status = equipment.getStatus();
        this.receiveDate = equipment.getReceiveDate();
        this.warrantyDate = equipment.getWarrantyDate();
        this.createdAt = equipment.getCreatedAt();
        this.updatedAt = equipment.getUpdatedAt();
        this.department = new DepartmentDto(equipment.getDepartment());
    }
}
