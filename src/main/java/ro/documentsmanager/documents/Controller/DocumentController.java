package ro.documentsmanager.documents.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.documentsmanager.documents.Entity.Document;
import ro.documentsmanager.documents.Service.DocumentService;

@RestController

public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/document/getAll")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    @PostMapping("/document/add")
    public Document createDocument(@RequestBody Document document) {
        return documentService.addDocument(document);
    }

    @GetMapping("/document/{id}")
    public Document getDocumentById(@PathVariable int id) {
        return documentService.getById(id);
    }

    @DeleteMapping("/document/delete/{id}")
    public void deleteDocument(@PathVariable int id) {
        documentService.deleteDocument(id);
    }

    @PutMapping("/document/update")
    public Document updateDocument(@RequestBody Document document) {
        return documentService.updateDocument(document);
    }

}
