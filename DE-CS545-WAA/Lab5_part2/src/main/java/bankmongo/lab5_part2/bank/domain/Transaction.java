package bankmongo.lab5_part2.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Transaction {
    private String description;
    private Double amount;
    private LocalDate date;

    public Transaction(String desc, Double amount) {
        this.description = desc;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "\n        Transaction{" +
                "  description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date + "}";
    }
}
