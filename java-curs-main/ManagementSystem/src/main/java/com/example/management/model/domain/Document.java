package com.example.management.model.domain;

import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "registration_number")
    private String registrationNumber; // UUID, simple ID or words and letters.
    private String title;
    private String keywords;
    private FileFormat format;
    private DocumentType type;

    private String link; // link to cloud storage.

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

//    @ManyToOne
//    @JoinColumn(name = "updated_user_id")
//    private User updatedBy;

//    @OneToMany(mappedBy = "document")
//    private List<Approval> approvals;
}
