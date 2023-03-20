package persons;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Message {

    @Id
    private String chatId;
    private String message;
    private LocalDate date;

    public String getMessage() {
         return this.message + " / " + this.date + " /";
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public  Message(String id, String msg) {
        super();
        this.chatId = id;
        this.message = msg;
        this.date = LocalDate.now();
    }

    public Message() { super(); }

    public String getChatId() {
        return this.chatId;
    }

    public void setChatId(String newId) {
        this.chatId = newId;
    }



}
