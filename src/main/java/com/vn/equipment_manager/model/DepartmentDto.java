package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }
}
