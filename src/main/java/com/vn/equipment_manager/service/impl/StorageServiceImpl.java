package com.vn.equipment_manager.service.impl;

import com.vn.equipment_manager.entity.Storage;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.mapper.StorageMapper;
import com.vn.equipment_manager.model.StorageDto;
import com.vn.equipment_manager.model.request.StorageRequest;
import com.vn.equipment_manager.repository.StorageRepository;
import com.vn.equipment_manager.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final StorageMapper storageMapper;

    @Override
    public List<StorageDto> getAll() {
        List<Storage> storages = storageRepository.findAll();
        return storages.stream()
                .map(storageMapper::toDtoWithoutEquipment).collect(Collectors.toList());
    }

    @Override
    public StorageDto getById(long id) {
        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Storage", "id", id));
        return new StorageDto(storage);
    }

    @Override
    public void create(StorageRequest request) {
        Storage storage = new Storage();
        storage.setName(request.getName());
        storage.setDescription(request.getDescription());
        storageRepository.save(storage);
    }

    @Override
    public void update(long id, StorageRequest request) {
        Storage storage = storageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Storage", "id", id));
        storage.setName(request.getName());
        storage.setDescription(request.getDescription());
        storageRepository.save(storage);
    }
}
