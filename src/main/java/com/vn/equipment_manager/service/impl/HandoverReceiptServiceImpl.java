package com.vn.equipment_manager.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.equipment_manager.entity.HandoverReceipt;
import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.exception.BadRequestException;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.HandoverReceiptDto;
import com.vn.equipment_manager.model.HandoverReceiptItem;
import com.vn.equipment_manager.model.request.HandoverReceiptRequest;
import com.vn.equipment_manager.repository.DepartmentRepository;
import com.vn.equipment_manager.repository.HandoverReceiptRepository;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.service.HandoverReceiptService;
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
public class HandoverReceiptServiceImpl implements HandoverReceiptService {
    private final HandoverReceiptRepository handoverReceiptRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<HandoverReceiptDto> search(String code, Integer status, Date handoverDate, String username, Long departmentId, Date createdAt, Date approvedAt, Pageable pageable) {
        Page<HandoverReceipt> handoverReceiptPage = handoverReceiptRepository.search(code, status, handoverDate, username,departmentId, createdAt, approvedAt, pageable);
        List<HandoverReceiptDto> handoverReceipts = handoverReceiptPage.stream()
                .map(HandoverReceiptDto::new).toList();
        return new PageImpl<>(handoverReceipts, pageable, handoverReceiptPage.getTotalElements());
    }

    @Override
    public HandoverReceiptDto getById(Long id) {
        HandoverReceipt handoverReceipt = handoverReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Import receipt", "id", id));
        HandoverReceiptDto dto = new HandoverReceiptDto(handoverReceipt);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<HandoverReceiptItem>>() {}.getType();
        dto.setItems(gson.fromJson(handoverReceipt.getItems(), listType));
        return dto;
    }

    @Override
    public void create(HandoverReceiptRequest request, Long userId) {
        if (handoverReceiptRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Code", request.getCode(), "Code is existed");
        }
        HandoverReceipt handoverReceipt = new HandoverReceipt();
        Gson gson = new Gson();
        handoverReceipt.setCode(request.getCode());
        handoverReceipt.setHandoverDate(request.getHandoverDate());
        handoverReceipt.setCreatedBy(userRepository.findUserById(userId));
        handoverReceipt.setStatus(EReceiptStatus.PENDING.getValue());
        handoverReceipt.setNote(request.getNote());
        handoverReceipt.setDepartment(departmentRepository.findDepartmentById(request.getDepartmentId()));
        handoverReceipt.setItems(request.getItems() != null ? gson.toJson(request.getItems()) : null);
        handoverReceiptRepository.save(handoverReceipt);
    }

    @Override
    public void update(Long id, HandoverReceiptRequest request) {

    }

    @Override
    public void changeStatus(Long id, Integer status, Long userId) {
        if (status > 2 || status < 0) {
            throw new BadRequestException("Status", status, "Status is not valid");
        }
        HandoverReceipt handoverReceipt = handoverReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Handover receipt", "id", id));
        if (handoverReceipt.getStatus() != 0) {
            throw new BadRequestException("Status", status, "Can not change status");
        }
        handoverReceipt.setStatus(status);
        handoverReceipt.setApprovedBy(userRepository.findUserById(userId));
        handoverReceipt.setApprovedAt(new Date());
        handoverReceiptRepository.save(handoverReceipt);
    }

    @Override
    public void delete(Long id) {
        HandoverReceipt handoverReceipt = handoverReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Handover receipt", "id", id));
        if (handoverReceipt.getStatus() != 0) {
            throw new BadRequestException("Handover Receipt", id, "Can not delete handover receipt");
        } else {
            handoverReceiptRepository.deleteById(id);
        }
    }
}
