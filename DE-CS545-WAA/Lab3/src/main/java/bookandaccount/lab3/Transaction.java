package bookandaccount.lab3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
