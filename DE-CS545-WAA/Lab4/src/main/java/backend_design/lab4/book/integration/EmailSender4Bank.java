package backend_design.lab4.book.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender4Bank {

    public void sendEmail (String message, String emailAddress) {
        System.out.println("Send email message '" + message + "' to" + emailAddress);
    }
}
