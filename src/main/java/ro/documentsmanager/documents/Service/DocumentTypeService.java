package ro.documentsmanager.documents.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.documentsmanager.documents.Entity.DocumentType;

import ro.documentsmanager.documents.Repositories.DocumentTypeRepository;

@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    public List<DocumentType> getAll() {
        return documentTypeRepository.findAll();
    }

    public DocumentType add(DocumentType type) {
        return documentTypeRepository.save(type);
    }

    public DocumentType findByName(String name) {
        return documentTypeRepository.findByName(name).orElse(null);
    }
}
