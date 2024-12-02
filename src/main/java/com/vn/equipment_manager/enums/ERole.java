package com.vn.equipment_manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERole {
    ADMIN(1, "ADMIN", "Quản trị viên"),
    QTTB(2, "QTTB", "Quản trị thiết bị"),
    BGH(3, "BGH", "Ban giám hiệu"),
    LDK(4, "LDK", "Lãnh đạo khoa");

    private final long id;
    private final String name;
    private final String displayName;
}
