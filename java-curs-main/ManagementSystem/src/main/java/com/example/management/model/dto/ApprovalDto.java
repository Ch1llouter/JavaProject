package com.example.management.model.dto;

import com.example.management.model.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApprovalDto {
    private Long documentId;
    private Long userId;
    private Status status;
    private LocalDateTime signedAt;
    private String signature;
}
