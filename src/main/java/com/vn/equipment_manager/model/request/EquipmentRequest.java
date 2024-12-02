package com.vn.equipment_manager.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequest {
    private String code;
    private Long typeId;
    private Integer status;
    private Date receiveDate;
    private Date warrantyDate;
    private Long departmentId;
    private Long storageId;
}
