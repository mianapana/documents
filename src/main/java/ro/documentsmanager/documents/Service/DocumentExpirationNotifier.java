package ro.documentsmanager.documents.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import ro.documentsmanager.documents.Entity.Document;
import ro.documentsmanager.documents.Repositories.DocumentRepository;

@Service
public class DocumentExpirationNotifier {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private JavaMailSender mailSender; // dacă trimiți emailuri


    @Scheduled(cron = "0 7 2 * * *") // La ora 01:29 în fiecare zi

    public void checkExpiringDocuments() {
        LocalDate today = LocalDate.now();
        LocalDate in3Days = today.plusDays(7);

        List<Document> expiringSoon = documentRepository.findByExpirationDateBetween(today, in3Days);

        for (Document doc : expiringSoon) {
            System.out.println("Trimit email pentru: " + doc.getTitle());
            sendNotification(doc);
        }
    }

    private void sendNotification(Document doc) {
        // Trimitere email sau log, după caz
        System.out.println("Documentul '" + doc.getTitle() + "' expira!");

        // Exemplu simplu cu email (dacă ai configurat Spring Mail)
        SimpleMailMessage message = new SimpleMailMessage();
 
        message.setTo("miana.pana@gmail.com");
        message.setSubject("Document expirat: " + doc.getTitle());
        message.setText("Documentul \"" + doc.getTitle() + "\" va expira la data de: " + doc.getExpirationDate());

        mailSender.send(message);
    }
}
