package com.vn.equipment_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageStatistic {
    private Long id;
    private String name;
    private Long distinctTypeCount;
    private Long totalEquipmentCount;
}
