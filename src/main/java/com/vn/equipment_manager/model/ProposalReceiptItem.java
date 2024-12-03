package com.vn.equipment_manager.model;

import lombok.Data;

@Data
public class ProposalReceiptItem {
    private String equipmentType;
    private Integer quantity;
    private String note;
}
