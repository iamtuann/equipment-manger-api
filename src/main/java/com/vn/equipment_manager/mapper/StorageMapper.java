package com.vn.equipment_manager.mapper;

import com.vn.equipment_manager.entity.Storage;
import com.vn.equipment_manager.model.EquipmentDto;
import com.vn.equipment_manager.model.StorageDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StorageMapper {

    public StorageDto toDtoWithoutEquipment(Storage storage) {
        StorageDto storageDto = new StorageDto();
        storageDto.setId(storage.getId());
        storageDto.setName(storage.getName());
        storageDto.setDescription(storage.getDescription());
        return storageDto;
    }

//    StorageDto toDto(Storage storage) {
//        StorageDto storageDto = new StorageDto();
//        storageDto.setId(storage.getId());
//        storageDto.setName(storage.getName());
//        storageDto.setDescription(storage.getDescription());
//        storageDto.setEquipments(storage.getEquipments().stream()
//                .map(EquipmentDto::new).collect(Collectors.toList()));
//        return storageDto;
//    }
}
