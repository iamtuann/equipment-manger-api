package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.ImportReceipt;
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
public class ImportReceiptDto {
    private Long id;
    private String code;
    private Date importDate;
    private String note;
    private UserBasic createdBy;
    private UserBasic approvedBy;
    private Integer status;
    private Double totalAmount;
    private Date createdAt;
    private Date updatedAt;
    private Date approvedAt;
    List<ImportReceiptItem> items;

    public ImportReceiptDto(ImportReceipt receipt) {
        this.id = receipt.getId();
        this.code = receipt.getCode();
        this.importDate = receipt.getImportDate();
        this.note = receipt.getNote();
        this.createdBy = new UserBasic(receipt.getCreatedBy());
        this.approvedBy = receipt.getApprovedBy() != null ? new UserBasic(receipt.getApprovedBy()) : null;
        this.status = receipt.getStatus();
        this.totalAmount = receipt.getTotalAmount();
        this.createdAt = receipt.getCreatedAt();
        this.updatedAt = receipt.getUpdatedAt();
        this.approvedAt = receipt.getApprovedAt();
    }
}
