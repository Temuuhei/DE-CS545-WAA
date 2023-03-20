package persons;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Repository
public interface MessageRepository extends ReactiveCrudRepository<Message, String> {

    @Tailable
    Flux<Message> findByDate(LocalDate date);
}
