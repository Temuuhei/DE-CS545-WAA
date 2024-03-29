package bookandaccount.lab3;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
//@NoArgsConstructor
public class Bank {
    private Integer accountNumber;
    private String accountHolder;
    private List<Transaction> transactionList = new ArrayList<>();
    private double balance;


    public Bank(Integer accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0;
    }

    public double getBalance() {
        return calcBalance();
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
