package com.vn.equipment_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDto {
    private Long id;
    private String receiptType;
    private String receiptCode;
    private Date approvedAt;
}
