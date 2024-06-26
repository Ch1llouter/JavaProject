package com.example.management.model.mapper;

import com.example.management.model.domain.Document;
import com.example.management.model.dto.DocumentDto;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {

    public DocumentDto toDto(Document document) {
        DocumentDto dto = new DocumentDto();
        if (document != null) {
            dto.setRegistrationNumber(document.getRegistrationNumber());
            dto.setTitle(document.getTitle());
            dto.setKeywords(document.getKeywords());
            dto.setFormat(document.getFormat());
            dto.setType(document.getType());
            dto.setLink(document.getLink());
        }
        return dto;
    }

    public Document toEntity(DocumentDto dto) {
        Document document = new Document();
        if (dto != null) {
            document.setRegistrationNumber(dto.getRegistrationNumber());
            document.setTitle(dto.getTitle());
            document.setKeywords(dto.getKeywords());
            document.setFormat(dto.getFormat());
            document.setType(dto.getType());
            document.setLink(dto.getLink());
        }
        return document;
    }
}
