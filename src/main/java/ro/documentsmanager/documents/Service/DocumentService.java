package ro.documentsmanager.documents.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import ro.documentsmanager.documents.Entity.Document;
import ro.documentsmanager.documents.Repositories.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();

    }

    public Document addDocument(@RequestBody Document doc) {
        return documentRepository.save(doc);
    }

    public Document getById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void deleteDocument(int id) {
        documentRepository.deleteById(id);
    }

    public Document updateDocument(@RequestBody Document updatedDocument) {
        Optional<Document> optionalDoc = documentRepository.findById(updatedDocument.getId());

        if (optionalDoc.isPresent()) {
            Document existingDoc = optionalDoc.get();
            existingDoc.setTitle(updatedDocument.getTitle());
            existingDoc.setExpirationDate(updatedDocument.getExpirationDate());
            // poți actualiza și alte câmpuri, dacă vrei
            return documentRepository.save(existingDoc);
        } else {
            throw new RuntimeException("Documentul nu a fost găsit");
        }
    }
}
