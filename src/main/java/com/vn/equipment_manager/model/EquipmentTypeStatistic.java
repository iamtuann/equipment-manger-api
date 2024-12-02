package com.vn.equipment_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentTypeStatistic {
    private Long id;
    private String name;
    private Double price;
    private Long activeCount;
    private Long inactiveCount;
    private Long inUseCount;
}
