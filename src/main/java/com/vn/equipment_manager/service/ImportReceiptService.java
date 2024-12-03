package com.vn.equipment_manager.service;

import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ImportReceiptService {

    List<ImportReceiptDto> getAll();

    Page<ImportReceiptDto> search(String code, Integer status, Date importDate, String username, Date createdAt, Date updatedAt, Pageable pageable);

    ImportReceiptDto getById(Long id);

    void create(ImportReceiptRequest request, Long userId);

    void update(Long id, ImportReceiptRequest request);

    void changeStatus(Long id, Integer status, Long userId);

    void delete(Long id);
}
