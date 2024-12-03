package com.vn.equipment_manager.model.request;

import com.vn.equipment_manager.model.HandoverReceiptItem;
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
public class HandoverReceiptRequest {
    private Long id;
    private String code;
    private Date handoverDate;
    private String note;
    private List<HandoverReceiptItem> items;
    private Long departmentId;
}
