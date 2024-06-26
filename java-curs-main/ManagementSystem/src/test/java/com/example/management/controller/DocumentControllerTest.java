package com.example.management.controller;

import com.example.management.model.dto.DocumentDto;
import com.example.management.model.dto.UserDto;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import com.example.management.model.enums.UserRole;
import com.example.management.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private DocumentController documentController;

    private DocumentDto expectedDocumentDto;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("user-001");
        userDto.setPassword("psw-01");
        userDto.setRole(UserRole.USER);

        expectedDocumentDto = new DocumentDto();
        expectedDocumentDto.setRegistrationNumber("01-001-3553-549-A");
        expectedDocumentDto.setTitle("title");
        expectedDocumentDto.setKeywords("k-1");
        expectedDocumentDto.setFormat(FileFormat.PDF);
        expectedDocumentDto.setType(DocumentType.APPLICATION);
        expectedDocumentDto.setLink("//href/main/main/bucket");
        expectedDocumentDto.setAuthor(userDto);
    }

    @Test
    void saveDocument() {
        DocumentDto dto = new DocumentDto();
        dto.setRegistrationNumber("01-001-3553-549-A");
        dto.setTitle("title");
        dto.setKeywords("k-1");
        dto.setType(DocumentType.APPLICATION);
        dto.setLink("//href/main/main/bucket");
        dto.setAuthor(userDto);

        when(documentService.saveDocument(any(DocumentDto.class))).thenReturn(expectedDocumentDto);
        ResponseEntity<DocumentDto> response = documentController.saveDocument(dto);
        assertNotNull(response.getBody());
        assertEquals(expectedDocumentDto.getType(), response.getBody().getType());
        assertEquals(expectedDocumentDto.getFormat(), response.getBody().getFormat());
        assertEquals(expectedDocumentDto.getAuthor().getId(), response.getBody().getAuthor().getId());
    }

    @Test
    void getById() {
        when(documentService.getDocumentById(anyLong())).thenReturn(expectedDocumentDto);
        ResponseEntity<? extends DocumentDto> response = documentController.getById(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedDocumentDto.getType(), response.getBody().getType());
        assertEquals(expectedDocumentDto.getFormat(), response.getBody().getFormat());
        assertEquals(expectedDocumentDto.getAuthor().getId(), response.getBody().getAuthor().getId());
    }

    @Test
    void searchDocumentsByTitle() {
        when(documentService.searchDocumentsByTitle(anyString())).thenReturn(List.of(expectedDocumentDto));

        ResponseEntity<List<DocumentDto>> response1 = documentController
                .searchDocuments("title", "", null);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertNotNull(response1.getBody());
        assertEquals(1, response1.getBody().size());
    }

    @Test
    void searchDocumentsByKeywords() {
        when(documentService.searchDocumentsByKeywords(anyString())).thenReturn(List.of(expectedDocumentDto));
        ResponseEntity<List<DocumentDto>> response = documentController
                .searchDocuments(null, "k-1", null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void searchDocumentsByAuthor() {
        when(documentService.searchDocumentsByAuthor(anyLong())).thenReturn(List.of(expectedDocumentDto));
        ResponseEntity<List<DocumentDto>> response = documentController
                .searchDocuments(null, null, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void delete() {
        doNothing().when(documentService).deleteDocument(anyLong());
        ResponseEntity<Void> response = documentController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}