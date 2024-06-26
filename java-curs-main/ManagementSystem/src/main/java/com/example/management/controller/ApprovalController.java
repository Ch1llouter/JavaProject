package com.example.management.controller;

import com.example.management.model.dto.ApprovalDto;
import com.example.management.service.ApprovalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approvals")
@Tag(name = "Управління підписаннями документів")
public class ApprovalController {

    private final ApprovalService approvalService;

    @Autowired
    public ApprovalController(@Qualifier("approvalServiceImpl") ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @Operation(description = "Створити новий зв'язок документа і користувача",
            summary = "Створення нового звя'зку між документом і користувачем"
    )
    @PostMapping
    public ResponseEntity<ApprovalDto> createApproval(@RequestParam Long documentId,
                                                      @RequestParam Long userId) {
        ApprovalDto approval = approvalService.createApproval(documentId, userId);
        if (approval == null) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(approval, HttpStatus.CREATED);
    }

    @Operation(description = "Підписати документ підписом і змінити статус документа",
            summary = "Створення підпису для документа"
    )
    @PutMapping("/{approvalId}/approve")
    public ResponseEntity<ApprovalDto> approveDocument(@PathVariable Long approvalId,
                                                       @RequestParam String status,
                                                       @RequestParam String signature) {
        ApprovalDto approvalDto = approvalService.approveDocument(approvalId, status, signature);
        return new ResponseEntity<>(approvalDto, HttpStatus.OK);
    }

    @Operation(description = "Видалити підписаний документ з статистики",
            summary = "Видалити підписаний документ з системи статистики"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        approvalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
