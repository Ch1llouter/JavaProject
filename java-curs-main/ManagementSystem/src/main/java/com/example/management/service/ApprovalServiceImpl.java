package com.example.management.service;

import com.example.management.exception.ApprovalNotFoundException;
import com.example.management.model.domain.Approval;
import com.example.management.model.domain.Document;
import com.example.management.model.domain.User;
import com.example.management.model.dto.ApprovalDto;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.Status;
import com.example.management.model.mapper.ApprovalMapper;
import com.example.management.repository.ApprovalRepository;
import com.example.management.repository.DocumentRepository;
import com.example.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class ApprovalServiceImpl implements ApprovalService {
    private final ReportService reportService;
    private ApprovalRepository approvalRepository;
    private DocumentRepository documentRepository;
    private UserRepository userRepository;
    private ApprovalMapper approvalMapper;

    @Autowired
    public ApprovalServiceImpl(ReportService reportService, ApprovalRepository approvalRepository,
                               DocumentRepository documentRepository,
                               UserRepository userRepository, ApprovalMapper approvalMapper) {
        this.reportService = reportService;
        this.approvalRepository = approvalRepository;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.approvalMapper = approvalMapper;
    }

    @Transactional
    public ApprovalDto createApproval(Long documentId, Long userId) {
        Optional<Document> document = documentRepository.findById(documentId);
        Optional<User> user = userRepository.findById(userId);
        if (document.isEmpty() || user.isEmpty()) {
            return null;
        }
        Approval saved = approvalRepository.save(new Approval(document.get(), user.get()));
        reportService.saveHistory(saved.getId(), Action.APPROVEMENT_CREATE, null, null);
        return approvalMapper.toDto(saved);
    }

    @Transactional
    public ApprovalDto approveDocument(Long approvalId, String status, String signature) {
        Approval approval = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new ApprovalNotFoundException(approvalId));
        if (approval.getStatus() == Status.NEW) {
            approval.setStatus(Status.valueOf(status));
            approval.setSignedAt(LocalDateTime.now());
            approval.setSignature(signature);
            Approval saved = approvalRepository.save(approval);
            reportService.saveHistory(saved.getId(), Action.APPROVEMENT_UPDATE, null, null);
            return approvalMapper.toDto(saved);
        }
        return approvalMapper.toDto(approval);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        approvalRepository.deleteById(id);
        reportService.saveHistory(id, Action.APPROVEMENT_DELETE, null, null);
    }
}
