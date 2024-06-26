package com.example.management.service;

import com.example.management.exception.DocumentNotFoundException;
import com.example.management.exception.UserNotFoundException;
import com.example.management.model.domain.Document;
import com.example.management.model.domain.User;
import com.example.management.model.dto.DocumentDto;
import com.example.management.model.enums.Action;
import com.example.management.model.mapper.DocumentMapper;
import com.example.management.repository.DocumentRepository;
import com.example.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DocumentServiceImpl implements DocumentService {
    private final ReportService reportService;
    private DocumentRepository documentRepository;
    private UserRepository userRepository;
    private DocumentMapper documentMapper;

    @Autowired
    public DocumentServiceImpl(ReportService reportService, DocumentRepository documentRepository,
                               UserRepository userRepository, DocumentMapper documentMapper) {
        this.reportService = reportService;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.documentMapper = documentMapper;
    }

    @Transactional
    public DocumentDto saveDocument(DocumentDto documentDto) {
        User author = userRepository.findByUsername(documentDto.getAuthor().getUsername())
                .orElseThrow(() -> new UserNotFoundException(documentDto.getAuthor().getUsername()));

        Document document = documentMapper.toEntity(documentDto);
        document.setAuthor(author);

        Document saved = documentRepository.save(document);
        reportService.saveHistory(saved.getId(), Action.DOCUMENT_CREATE,
                documentDto.getFormat(), documentDto.getType());
        return documentMapper.toDto(saved);
    }

    public DocumentDto getDocumentById(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
        return documentMapper.toDto(document);
    }

    public List<DocumentDto> getAllDocuments() {
        List<Document> documents = documentRepository.findAll();
        return documents.stream().map(document -> documentMapper.toDto(document)).toList();
    }

    public List<DocumentDto> searchDocumentsByTitle(String title) {
        List<Document> documents = documentRepository.findByTitle(title);
        return documents.stream().map(document -> documentMapper.toDto(document)).toList();
    }

    public List<DocumentDto> searchDocumentsByKeywords(String keywords) {
        List<Document> documents = documentRepository.findByKeywords(keywords);
        return documents.stream().map(document -> documentMapper.toDto(document)).toList();
    }

    public List<DocumentDto> searchDocumentsByAuthor(Long authorId) {
        List<Document> documents = documentRepository.findByAuthor(authorId);
        return documents.stream().map(document -> documentMapper.toDto(document)).toList();
    }

    @Transactional
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
        reportService.saveHistory(id, Action.DOCUMENT_DELETE, null, null);
    }
}
