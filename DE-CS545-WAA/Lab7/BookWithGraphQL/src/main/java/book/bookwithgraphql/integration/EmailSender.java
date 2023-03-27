package book.bookwithgraphql.integration;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    public void sendEmail(String message, String action) {
        System.out.println("Send notification message '" + message +"' this book is  " + action);
    }
}
