package ro.documentsmanager.documents.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import ro.documentsmanager.documents.Entity.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
    Optional<DocumentType> findByName(String name);

    
}
