package com.vn.equipment_manager.model;

import com.vn.equipment_manager.entity.Storage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageDto {
    private Long id;
    private String name;
    private String description;

    public StorageDto(Storage storage) {
        this.id = storage.getId();
        this.name = storage.getName();
        this.description = storage.getDescription();
    }
}
