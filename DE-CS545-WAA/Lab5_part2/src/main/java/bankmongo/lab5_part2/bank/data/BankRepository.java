package bankmongo.lab5_part2.bank.data;

import bankmongo.lab5_part2.bank.domain.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository <Bank, Integer> {

    @Query("{accountNumber : ?0}")
    Bank findByAccountNumber(Integer accountNumber);
}
