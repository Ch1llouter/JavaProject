package com.example.management.model.domain;

import com.example.management.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "approvals")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    private String signature;

    public Approval(Document document, User user) {
        this.document = document;
        this.user = user;
        this.status = Status.NEW;
    }
}
