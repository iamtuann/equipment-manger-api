package com.vn.equipment_manager.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.equipment_manager.entity.ImportReceipt;
import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.exception.BadRequestException;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.ImportReceiptDto;
import com.vn.equipment_manager.model.ImportReceiptItem;
import com.vn.equipment_manager.model.request.ImportReceiptRequest;
import com.vn.equipment_manager.repository.ImportReceiptRepository;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.service.ImportReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ImportReceiptServiceImpl implements ImportReceiptService {
    private final ImportReceiptRepository importReceiptRepository;
    private final UserRepository userRepository;

    @Override
    public List<ImportReceiptDto> getAll() {
        List<ImportReceipt> importReceipts = importReceiptRepository.findAll();
        return importReceipts.stream()
                .map(ImportReceiptDto::new).toList();
    }

    @Override
    public Page<ImportReceiptDto> search(String code, Integer status, Date importDate, String username, Date createdAt, Date updatedAt, Pageable pageable) {
        Page<ImportReceipt> importReceiptPage = importReceiptRepository.search(code, status, importDate, username, createdAt, updatedAt, pageable);
        List<ImportReceiptDto> importReceipts = importReceiptPage.stream()
                .map(ImportReceiptDto::new).toList();
        return new PageImpl<>(importReceipts, pageable, importReceiptPage.getTotalElements());
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
        if (importReceiptRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Code", request.getCode(), "Code is existed");
        }
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
    public void changeStatus(Long id, Integer status, Long userId) {
        if (status > 2 || status < 0) {
            throw new BadRequestException("Status", status, "Status is not valid");
        }
        ImportReceipt importReceipt = importReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import receipt", "id", id));
        if (importReceipt.getStatus() != 0) {
            throw new BadRequestException("Status", status, "Can not change status");
        }
        importReceipt.setStatus(status);
        importReceipt.setApprovedBy(userRepository.findUserById(userId));
        importReceipt.setApprovedAt(new Date());
        importReceiptRepository.save(importReceipt);
    }

    @Override
    public void delete(Long id) {
        ImportReceipt importReceipt = importReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import receipt", "id", id));
        if (importReceipt.getStatus() != 0) {
            throw new BadRequestException("Import Receipt", id, "Can not delete import receipt");
        } else {
            importReceiptRepository.deleteById(id);
        }
    }
}
