package ro.documentsmanager.documents.Entity;

import java.time.LocalDate;
import java.util.Optional;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ro.documentsmanager.documents.Repositories.DocumentRepository;
import ro.documentsmanager.documents.Repositories.DocumentTypeRepository;
import ro.documentsmanager.documents.Repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final DocumentTypeRepository documentTypeRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;

    public DataLoader(DocumentTypeRepository documentTypeRepository,
            UserRepository userRepository,
            DocumentRepository documentRepository) {
        this.documentTypeRepository = documentTypeRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public void run(String... args) {
        // DocumentType (doar dacă nu există)
        String[] types = { "CI", "Pasaport", "Permis Auto" };
        for (String type : types) {
            documentTypeRepository.findByName(type)
                    .or(() -> Optional.of(documentTypeRepository.save(new DocumentType(type))));
        }

        // User (doar dacă nu există)
        String email = "admin@example.com";
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword("hashedpassword"); // Ai grijă: trebuie să fie deja hash-uit dacă folosești Spring
                                                   // Security
            newUser.setCreatedAt(LocalDate.now());
            return userRepository.save(newUser);
        });

       
    }
}