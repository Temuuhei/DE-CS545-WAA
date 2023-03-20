package persons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    private int x = 1;

    @GetMapping(value="/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> getChatMessages() {
        return messageRepository.findByDate(LocalDate.now());
    }

    @Scheduled(fixedRate = 3000)
    private void saveMessage() {
        System.out.println("New chat message is added" + " " + x);
        messageRepository.save(new Message(x + "_chat_id", "How are you guys ?")).block();
        x++;
    }
}
