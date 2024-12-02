package com.vn.equipment_manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EReceiptStatus {
    PENDING(0, "Chờ phê duyệt"),
    APPROVED(1, "Chấp nhận"),
    REJECTED(2, "Từ chối");

    private final Integer value;
    private final String name;
}
