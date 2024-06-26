package com.example.management.model.dto;

import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import lombok.Data;

@Data
public class DocumentDto {
    private String registrationNumber;
    private String title;
    private String keywords;
    private FileFormat format;
    private DocumentType type;
    private String link;
    private UserDto author;
}
