package com.example.management.controller;

import com.example.management.model.dto.HistoryDto;
import com.example.management.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Управління історією подій в системі")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(description = "Отримати історію документації за форматом або типом документів",
            summary = "Отримати інформацію про роботу з документами за форматом або типом документів"
    )
    @GetMapping("/documents")
    public ResponseEntity<List<?>> getDocumentsByFormatOrType(@RequestParam(required = false) String format,
                                                  @RequestParam(required = false) String type) {
        if (format != null && !format.isEmpty()) {
            List<HistoryDto> results = reportService.getDocumentsByFormat(format);
            return new ResponseEntity<>(results, HttpStatus.OK);
        } else if (type != null && !type.isEmpty()) {
            List<HistoryDto> results = reportService.getDocumentsByType(type);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "Отримати історію про видалені документи",
            summary = "Отримати інформацію про видалені документи"
    )
    @GetMapping("/documents/deleted")
    public ResponseEntity<List<? extends HistoryDto>> getDeletedDocuments() {
        List<HistoryDto> results = reportService.getDeletedDocuments();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Operation(description = "Отримати історію про видалених користувачів",
            summary = "Отримати інформацію про видалених користувачів"
    )
    @GetMapping("/users/deleted")
    public ResponseEntity<?> getDeletedUsers() {
        List<HistoryDto> results = reportService.getDeletedUsers();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
