package com.example.management.model.mapper;

import com.example.management.model.domain.History;
import com.example.management.model.dto.HistoryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryMapper {

    public HistoryDto toDto(History history) {
        HistoryDto dto = new HistoryDto();
        if (history != null) {
            dto.setEntityId(history.getEntityId());
            dto.setAction(history.getAction());
            dto.setFormat(history.getFormat());
            dto.setType(history.getType());
        }
        return dto;
    }

    public List<HistoryDto> toDtoList(List<History> historyList) {
        return historyList.stream().map(this::toDto).toList();
    }
}
