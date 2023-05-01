package bankmongo.lab5_part2.bank.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@Document
public class Bank {
    @Id
    private Integer accountNumber;
    private String accountHolder;
    private List<Transaction> transactionList = new ArrayList<>();

    public Bank (Integer accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public double calcBalance() {
        return transactionList.stream().mapToDouble(t -> t.getAmount()).sum();
    }

    @Override
    public String toString() {
        return "Bank{ " +
                "\n  accountNumber=" + accountNumber +
                "\n  accountHolder='" + accountHolder + '\'' +
                "\n  balance='" + calcBalance() + '\'' +
                "\n  transactionList=" + transactionList +" \n }";
    }
}
