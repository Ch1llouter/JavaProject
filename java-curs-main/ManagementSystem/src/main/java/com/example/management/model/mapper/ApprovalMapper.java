package com.example.management.model.mapper;

import com.example.management.model.domain.Approval;
import com.example.management.model.dto.ApprovalDto;
import org.springframework.stereotype.Component;

@Component
public class ApprovalMapper {

    public ApprovalDto toDto(Approval approval) {
        ApprovalDto dto = new ApprovalDto();
        if (approval != null) {
            dto.setDocumentId(approval.getDocument().getId());
            dto.setUserId(approval.getUser().getId());
            dto.setStatus(approval.getStatus());
            dto.setSignedAt(approval.getSignedAt());
            dto.setSignature(approval.getSignature());
        }
        return dto;
    }
}
