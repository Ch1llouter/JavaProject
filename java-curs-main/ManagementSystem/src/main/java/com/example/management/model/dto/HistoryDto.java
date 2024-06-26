package com.example.management.model.dto;

import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import lombok.Data;

@Data
public class HistoryDto {
    private Long entityId;
    private Action action;
    private FileFormat format;
    private DocumentType type;
}
