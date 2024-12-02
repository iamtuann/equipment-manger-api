package com.vn.equipment_manager.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.equipment_manager.entity.ImportReceipt;
import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.ImportReceiptItem;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;
import com.vn.equipment_manager.repository.ImportReceiptRepository;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.service.ImportReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportReceiptServiceImpl implements ImportReceiptService {
    private final ImportReceiptRepository importReceiptRepository;
    private final UserRepository userRepository;

    @Override
    public List<ImportReceiptDto> getAllByStatus(Integer status) {
        List<ImportReceipt> importReceipts = importReceiptRepository.findAllByStatus(status);
        return importReceipts.stream()
                .map(ImportReceiptDto::new).toList();
    }

    @Override
    public List<ImportReceiptDto> getAll() {
        List<ImportReceipt> importReceipts = importReceiptRepository.findAll();
        return importReceipts.stream()
                .map(ImportReceiptDto::new).toList();
    }

    @Override
    public ImportReceiptDto getById(Long id) {
        ImportReceipt importReceipt = importReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import receipt", "id", id));
        ImportReceiptDto dto = new ImportReceiptDto(importReceipt);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ImportReceiptItem>>() {}.getType();
        dto.setItems(gson.fromJson(importReceipt.getItems(), listType));
        return dto;
    }

    @Override
    public void create(ImportReceiptRequest request, Long userId) {
        ImportReceipt importReceipt = new ImportReceipt();
        Gson gson = new Gson();
        importReceipt.setCode(request.getCode());
        importReceipt.setImportDate(request.getImportDate());
        importReceipt.setTotalAmount(request.getTotalAmount());
        importReceipt.setCreatedBy(userRepository.findUserById(userId));
        importReceipt.setStatus(EReceiptStatus.PENDING.getValue());
        importReceipt.setNote(request.getNote());
        importReceipt.setItems(request.getItems() != null ? gson.toJson(request.getItems()) : null);
        importReceiptRepository.save(importReceipt);
    }

    @Override
    public void update(Long id, ImportReceiptRequest request) {

    }

    @Override
    public void changeStatus(Long id, Integer status) {

    }

    @Override
    public void delete(Long id) {

    }
}
