package com.example.management.repository;

import com.example.management.model.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d FROM Document d WHERE LOWER(d.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Document> findByTitle(@Param("title") String title);

    @Query("SELECT d FROM Document d WHERE LOWER(d.keywords) LIKE LOWER(CONCAT('%', :keywords, '%'))")
    List<Document> findByKeywords(@Param("keywords") String keywords);

    @Query("SELECT d FROM Document d WHERE d.author.id = :authorId")
    List<Document> findByAuthor(@Param("authorId") Long authorId);
}
