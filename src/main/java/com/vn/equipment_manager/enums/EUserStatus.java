package com.vn.equipment_manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EUserStatus {
    ACTIVE(1, "Hoạt động"),
    INACTIVE(0, "Không hoạt động");

    private final int value;
    private final String name;
}
