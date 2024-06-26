package com.example.management.repository;

import com.example.management.model.domain.History;
import com.example.management.model.enums.Action;
import com.example.management.model.enums.DocumentType;
import com.example.management.model.enums.FileFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

//    @Query("SELECT h FROM History h WHERE h.format = ?1")
//    List<History> findHistoriesByFormat(String format); // findDocumentsByFormat

    List<History> findByFormat(FileFormat format);

//    @Query("SELECT h FROM History h WHERE h.type = ?1")
//    List<History> findHistoriesByType(String type); // findDocumentsByType

    List<History> findByType(DocumentType type);

//    @Query("SELECT h FROM History h WHERE h.action = ?1")
//    List<History> findHistoriesByAction(Action action); //findDeletedUsers();

    List<History> findByAction(Action action);
}
