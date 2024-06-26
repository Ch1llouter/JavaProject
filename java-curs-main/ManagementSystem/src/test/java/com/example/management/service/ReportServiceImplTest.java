package com.example.management.service;

import com.example.management.model.domain.History;
import com.example.management.model.dto.HistoryDto;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import com.example.management.model.mapper.HistoryMapper;
import com.example.management.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private HistoryMapper historyMapper;

    @InjectMocks
    private ReportServiceImpl reportService;

    private History expected;
    private HistoryDto expectedDto;

    @BeforeEach
    void setUp() {
        expected = new History();
        expected.setId(1L);
        expected.setEntityId(1L);
        expected.setAction(Action.DOCUMENT_DELETE);

        expectedDto = new HistoryDto();
        expectedDto.setEntityId(1L);
        expectedDto.setAction(Action.DOCUMENT_DELETE);
    }

    @Test
    void getDocumentsByFormat() {
        when(historyRepository.findByFormat(any(FileFormat.class))).thenReturn(List.of(expected));
        when(historyMapper.toDtoList(any())).thenReturn(List.of(expectedDto));

        List<HistoryDto> result = reportService.getDocumentsByFormat(FileFormat.PDF.name());
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(expectedDto.getEntityId(), result.get(0).getEntityId());
        verify(historyRepository, times(1)).findByFormat(any(FileFormat.class));
        verify(historyMapper, times(1)).toDtoList(any());
    }

    @Test
    void getDocumentsByType() {
        when(historyRepository.findByType(any(DocumentType.class))).thenReturn(List.of(expected));
        when(historyMapper.toDtoList(any())).thenReturn(List.of(expectedDto));

        List<HistoryDto> result = reportService.getDocumentsByType(DocumentType.APPLICATION.name());
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(historyRepository, times(1)).findByType(any(DocumentType.class));
        verify(historyMapper, times(1)).toDtoList(any());
    }

    @Test
    void getDeletedDocuments() {
        when(historyRepository.findByAction(any(Action.class))).thenReturn(List.of(expected));
        when(historyMapper.toDtoList(any())).thenReturn(List.of(expectedDto));

        List<HistoryDto> result = reportService.getDeletedDocuments();
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(historyRepository, times(1)).findByAction(any(Action.class));
        verify(historyMapper, times(1)).toDtoList(any());
    }

    @Test
    void getDeletedUsers() {
        when(historyRepository.findByAction(any(Action.class))).thenReturn(Collections.emptyList());
        when(historyMapper.toDtoList(any())).thenReturn(Collections.emptyList());

        List<HistoryDto> result = reportService.getDeletedUsers();
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(historyRepository, times(1)).findByAction(any(Action.class));
        verify(historyMapper, times(1)).toDtoList(any());
    }

    @Test
    void saveHistory() {
        when(historyRepository.save(any(History.class))).thenReturn(expected);
        History result = reportService.saveHistory(1L, Action.DOCUMENT_DELETE, null, null);
        assertNotNull(result);
        verify(historyRepository, times(1)).save(any(History.class));
    }
}