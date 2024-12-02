package com.vn.equipment_manager.model;

import lombok.Data;

@Data
public class ImportReceiptItem {
    private String equipmentType;
    private Integer quantity;
    private Double unitPrice;
    private String note;
}
