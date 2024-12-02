package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.StorageDto;
import com.vn.equipment_manager.model.request.StorageRequest;

import java.util.List;

public interface StorageService {
    List<StorageDto> getAll();

    StorageDto getById(long id);

    void create(StorageRequest request);

    void update(long id, StorageRequest request);
}
