package com.example.management.service;

import com.example.management.model.domain.History;
import com.example.management.model.dto.HistoryDto;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import com.example.management.model.mapper.HistoryMapper;
import com.example.management.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    @Autowired
    public ReportServiceImpl(HistoryRepository historyRepository, HistoryMapper historyMapper) {
        this.historyRepository = historyRepository;
        this.historyMapper = historyMapper;
    }

    @Override
    public List<HistoryDto> getDocumentsByFormat(String format) {
        FileFormat of = FileFormat.valueOf(format);
        List<History> histories = historyRepository.findByFormat(of);
        return historyMapper.toDtoList(histories);
    }

    @Override
    public List<HistoryDto> getDocumentsByType(String type) {
        List<History> histories = historyRepository.findByType(DocumentType.valueOf(type));
        return historyMapper.toDtoList(histories);
    }

    @Override
    public List<HistoryDto> getDeletedDocuments() {
        List<History> histories = historyRepository.findByAction(Action.DOCUMENT_DELETE);
        return historyMapper.toDtoList(histories);
    }

    @Override
    public List<HistoryDto> getDeletedUsers() {
        List<History> histories = historyRepository.findByAction(Action.USER_DELETE);
        return historyMapper.toDtoList(histories);
    }

    @Override
    public History saveHistory(Long entityId, Action action, FileFormat format, DocumentType type) {
        History history = new History();
        history.setEntityId(entityId);
        history.setAction(action);
        history.setFormat(format);
        history.setType(type);
        return historyRepository.save(history);
    }
}

