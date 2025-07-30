package ro.documentsmanager.documents.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.documentsmanager.documents.Entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByUserId(Integer id);

    List<Document> findByExpirationDateBetween(LocalDate start, LocalDate end);

}
