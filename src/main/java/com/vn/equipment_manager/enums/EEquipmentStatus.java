package com.vn.equipment_manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EEquipmentStatus {
    WORKING(1, "Tốt"),
    NOT_WORKING(0, "Hỏng");

    private final long value;
    private final String name;
}
