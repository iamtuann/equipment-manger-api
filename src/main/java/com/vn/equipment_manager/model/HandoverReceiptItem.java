package com.vn.equipment_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandoverReceiptItem {
    private String equipmentType;
    private Integer quantity;
    private String note;
}
