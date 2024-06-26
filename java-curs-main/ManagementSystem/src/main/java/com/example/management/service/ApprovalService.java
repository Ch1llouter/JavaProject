package com.example.management.service;

import com.example.management.model.dto.ApprovalDto;
import org.springframework.stereotype.Component;

@Component
public interface ApprovalService {
    ApprovalDto createApproval(Long documentId, Long userId);
    ApprovalDto approveDocument(Long approvalId, String status, String signature);
    void delete(Long id);
}
