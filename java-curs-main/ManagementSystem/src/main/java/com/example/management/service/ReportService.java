package com.example.management.service;

import com.example.management.model.domain.History;
import com.example.management.model.dto.HistoryDto;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReportService {
    List<HistoryDto> getDocumentsByFormat(String format);
    List<HistoryDto> getDocumentsByType(String type);
    List<HistoryDto> getDeletedDocuments();
    List<HistoryDto> getDeletedUsers();

    History saveHistory(Long entityId, Action action, FileFormat format, DocumentType type);
}
