package com.vn.equipment_manager.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vn.equipment_manager.entity.ProposalReceipt;
import com.vn.equipment_manager.enums.EReceiptStatus;
import com.vn.equipment_manager.exception.BadRequestException;
import com.vn.equipment_manager.exception.ResourceNotFoundException;
import com.vn.equipment_manager.model.ProposalReceiptDto;
import com.vn.equipment_manager.model.ProposalReceiptItem;
import com.vn.equipment_manager.model.request.ProposalReceiptRequest;
import com.vn.equipment_manager.repository.DepartmentRepository;
import com.vn.equipment_manager.repository.ProposalReceiptRepository;
import com.vn.equipment_manager.repository.UserRepository;
import com.vn.equipment_manager.service.ProposalReceiptService;
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
public class ProposalReceiptServiceImpl implements ProposalReceiptService {
    private final ProposalReceiptRepository proposalReceiptRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<ProposalReceiptDto> search(String code, Integer status, Date proposalDate, String username, Long departmentId, Date createdAt, Date approvedAt, Pageable pageable) {
        Page<ProposalReceipt> proposalReceiptPage = proposalReceiptRepository.search(code, status, proposalDate, username,departmentId, createdAt, approvedAt, pageable);
        List<ProposalReceiptDto> proposalReceipts = proposalReceiptPage.stream()
                .map(ProposalReceiptDto::new).toList();
        return new PageImpl<>(proposalReceipts, pageable, proposalReceiptPage.getTotalElements());
    }

    @Override
    public ProposalReceiptDto getById(Long id) {
        ProposalReceipt proposalReceipt = proposalReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposal receipt", "id", id));
        ProposalReceiptDto dto = new ProposalReceiptDto(proposalReceipt);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProposalReceiptItem>>() {}.getType();
        dto.setItems(gson.fromJson(proposalReceipt.getItems(), listType));
        return dto;
    }

    @Override
    public void create(ProposalReceiptRequest request, Long userId) {
        if (proposalReceiptRepository.existsByCode(request.getCode())) {
            throw new BadRequestException("Code", request.getCode(), "Code is existed");
        }
        ProposalReceipt proposalReceipt = new ProposalReceipt();
        Gson gson = new Gson();
        proposalReceipt.setCode(request.getCode());
        proposalReceipt.setProposalDate(request.getProposalDate());
        proposalReceipt.setCreatedBy(userRepository.findUserById(userId));
        proposalReceipt.setStatus(EReceiptStatus.PENDING.getValue());
        proposalReceipt.setNote(request.getNote());
        proposalReceipt.setDepartment(departmentRepository.findDepartmentById(request.getDepartmentId()));
        proposalReceipt.setItems(request.getItems() != null ? gson.toJson(request.getItems()) : null);
        proposalReceiptRepository.save(proposalReceipt);
    }

    @Override
    public void update(Long id, ProposalReceiptRequest request) {

    }

    @Override
    public void changeStatus(Long id, Integer status, Long userId) {
        if (status > 2 || status < 0) {
            throw new BadRequestException("Status", status, "Status is not valid");
        }
        ProposalReceipt proposalReceipt = proposalReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposal receipt", "id", id));
        if (proposalReceipt.getStatus() != 0) {
            throw new BadRequestException("Status", status, "Can not change status");
        }
        proposalReceipt.setStatus(status);
        proposalReceipt.setApprovedBy(userRepository.findUserById(userId));
        proposalReceipt.setApprovedAt(new Date());
        proposalReceiptRepository.save(proposalReceipt);
    }

    @Override
    public void delete(Long id) {
        ProposalReceipt proposalReceipt = proposalReceiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposal receipt", "id", id));
        if (proposalReceipt.getStatus() != 0) {
            throw new BadRequestException("Proposal Receipt", id, "Can not delete proposal receipt");
        } else {
            proposalReceiptRepository.deleteById(id);
        }
    }
}
