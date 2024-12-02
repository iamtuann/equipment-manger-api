package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeDto {
    private Long id;
    private String name;
    private Double price;
    private Date createdAt;
    private Date updatedAt;

    public EquipmentTypeDto(EquipmentType type) {
        this.id = type.getId();
        this.name = type.getName();
        this.price = type.getPrice();
        this.createdAt = type.getCreatedAt();
        this.updatedAt = type.getUpdatedAt();
    }
}
