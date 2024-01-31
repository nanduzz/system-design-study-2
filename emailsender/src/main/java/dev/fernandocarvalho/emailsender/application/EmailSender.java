package dev.fernandocarvalho.emailsender.application;


import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    public void send(String email, String message, String title) {
        System.out.printf("Sending email to %s with title %s and message %s%n", email, title, message);
    }
}
