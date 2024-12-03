package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.HandoverReceipt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandoverReceiptDto {
    private Long id;
    private String code;
    private Date handoverDate;
    private String note;
    private UserBasic createdBy;
    private UserBasic approvedBy;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Date approvedAt;
    private DepartmentDto department;
    List<HandoverReceiptItem> items;

    public HandoverReceiptDto(HandoverReceipt receipt) {
        this.id = receipt.getId();
        this.code = receipt.getCode();
        this.handoverDate = receipt.getHandoverDate();
        this.note = receipt.getNote();
        this.createdBy = new UserBasic(receipt.getCreatedBy());
        this.approvedBy = receipt.getApprovedBy() != null ? new UserBasic(receipt.getApprovedBy()) : null;
        this.status = receipt.getStatus();
        this.createdAt = receipt.getCreatedAt();
        this.updatedAt = receipt.getUpdatedAt();
        this.approvedAt = receipt.getApprovedAt();
        this.department = new DepartmentDto(receipt.getDepartment());
    }
}
