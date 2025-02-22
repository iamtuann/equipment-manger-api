package com.vn.equipment_manager.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDepartmentRequest {
    private Long departmentId;
    private List<Long> equipmentIds;
}
