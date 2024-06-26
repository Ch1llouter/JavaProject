package com.example.management.controller;

import com.example.management.model.dto.ApprovalDto;
import com.example.management.model.dto.DocumentDto;
import com.example.management.model.enums.Status;
import com.example.management.service.ApprovalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApprovalControllerTest {

    @Mock
    private ApprovalService approvalservice;

    @InjectMocks
    private ApprovalController approvalController;

    private ApprovalDto expectedApprovalDto;

    @BeforeEach
    void setUp() {
        expectedApprovalDto = new ApprovalDto();
        expectedApprovalDto.setDocumentId(1L);
        expectedApprovalDto.setUserId(1L);
    }

    @Test
    void createApproval() {
        ApprovalDto expectedDto = new ApprovalDto();
        expectedDto.setDocumentId(1L);
        expectedDto.setUserId(1L);
        when(approvalservice.createApproval(anyLong(), anyLong())).thenReturn(expectedDto);

        ResponseEntity<ApprovalDto> response = approvalController.createApproval(1L, 1L);
        assertNotNull(response.getBody());
        assertEquals(expectedDto.getDocumentId(), response.getBody().getDocumentId());
        assertEquals(expectedDto.getUserId(), response.getBody().getUserId());
        assertNull(response.getBody().getSignature());
        assertNull(response.getBody().getSignedAt());
        assertNull(response.getBody().getStatus());
    }

    @Test
    void approveDocument() {
        expectedApprovalDto.setSignature("sign-01");
        expectedApprovalDto.setStatus(Status.CONTROL);
        expectedApprovalDto.setSignedAt(LocalDateTime.now());

        when(approvalservice.approveDocument(anyLong(), anyString(), anyString())).thenReturn(expectedApprovalDto);
        ResponseEntity<ApprovalDto> response = approvalController
                .approveDocument(1L, Status.CONTROL.name(), "sign-01");
        assertNotNull(response.getBody());
        assertEquals(expectedApprovalDto.getDocumentId(), response.getBody().getDocumentId());
        assertEquals(expectedApprovalDto.getUserId(), response.getBody().getUserId());
        assertEquals(expectedApprovalDto.getSignature(), response.getBody().getSignature());
        assertEquals(Status.CONTROL, response.getBody().getStatus());
        assertNotNull(response.getBody().getSignedAt());
    }

    @Test
    void delete() {
        doNothing().when(approvalservice).delete(anyLong());
        ResponseEntity<Void> response = approvalController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}