package com.mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    private final JavaMailSender javaMailSender;

    public ContactController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitContactForm(@RequestBody Modal contactForm) {
        // Process the form data and send an email
        try {
            return ResponseEntity.ok("Form submitted successfully.");
        } catch (MailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email could not be sent.");
        }
    }

    private void sendEmail(Modal contactForm) throws MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("your-email@example.com"); // Replace with your email address
        mailMessage.setSubject(contactForm.getSubject());
        mailMessage.setText("Name: " + contactForm.getName() +
                            "\nEmail: " + contactForm.getEmail() +
                            "\nMessage:\n" + contactForm.getMessage());

        javaMailSender.send(mailMessage);
    }
}
