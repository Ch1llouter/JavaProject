package com.example.management.controller;

import com.example.management.model.dto.DocumentDto;
import com.example.management.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@Tag(name = "Управління документами")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Operation(description = "Реєстрація нового документа в системі",
            summary = "Створення нового документа"
    )
    @PostMapping
    public ResponseEntity<DocumentDto> saveDocument(@RequestBody DocumentDto dto) {
        DocumentDto savedDocument = documentService.saveDocument(dto);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @Operation(description = "Отримати інформацію про документ",
            summary = "Отримати інформацію про обраний документ"
    )
    @GetMapping("/{id}")
    public ResponseEntity<? extends DocumentDto> getById(@PathVariable Long id) {
        DocumentDto document = documentService.getDocumentById(id);
        return document != null ? new ResponseEntity<>(document, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Отримати інформацію про документи за фільтрами",
            summary = "Отримати список документів за обраними фільтрами"
    )
    @GetMapping("/search")
    public ResponseEntity<List<DocumentDto>> searchDocuments(@RequestParam(required = false) String title,
                                                         @RequestParam(required = false) String keywords,
                                                         @RequestParam(required = false) Long authorId) {
        List<DocumentDto> documents;
        if (title != null) {
            documents = documentService.searchDocumentsByTitle(title);
        } else if (keywords != null) {
            documents = documentService.searchDocumentsByKeywords(keywords);
        } else if (authorId != null) {
            documents = documentService.searchDocumentsByAuthor(authorId);
        } else {
            documents = documentService.getAllDocuments();
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @Operation(description = "Видалити документ",
            summary = "Видалити документ з системи"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("DocumentController.delete. id = " + id);
        documentService.deleteDocument(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
