package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;

import java.util.List;

public interface ImportReceiptService {
    List<ImportReceiptDto> getAllByStatus(Integer status);

    List<ImportReceiptDto> getAll();

    ImportReceiptDto getById(Long id);

    void create(ImportReceiptRequest request, Long userId);

    void update(Long id, ImportReceiptRequest request);

    void changeStatus(Long id, Integer status);

    void delete(Long id);
}
