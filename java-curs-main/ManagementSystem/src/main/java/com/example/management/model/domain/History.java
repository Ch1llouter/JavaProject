package com.example.management.model.domain;

import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_id")
    private Long entityId; // for example: userId, documentId, approvalId;

    @Column(name = "action")
    private Action action; // USER_CREATE, USER_DELETE, DOCUMENT_DELETE, APPROVEMENT_CREATE

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "format")
    private FileFormat format; // file format: pdf.

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private DocumentType type; // if document: document type: contract, application.
}
