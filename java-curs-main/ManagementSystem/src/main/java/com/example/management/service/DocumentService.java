package com.example.management.service;

import com.example.management.model.dto.DocumentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DocumentService {
    DocumentDto saveDocument(DocumentDto documentDto);
    DocumentDto getDocumentById(Long id);
    List<DocumentDto> getAllDocuments();
    List<DocumentDto> searchDocumentsByTitle(String title);
    List<DocumentDto> searchDocumentsByAuthor(Long authorId);
    List<DocumentDto> searchDocumentsByKeywords(String keywords);
    void deleteDocument(Long id);
}
