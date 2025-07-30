package ro.documentsmanager.documents.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.documentsmanager.documents.Entity.DocumentType;
import ro.documentsmanager.documents.Service.DocumentTypeService;

@RestController

public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/documentType/getAll")
    public List<DocumentType> getAllTypes() {
        return documentTypeService.getAll();
    }

    @PostMapping("/documentType/add")
    public DocumentType addType(@RequestBody DocumentType type) {
        return documentTypeService.add(type);
    }

    @GetMapping("/documentType/by-name/{name}")
    public DocumentType getByName(@PathVariable String name) {
        return documentTypeService.findByName(name);
    }

}
