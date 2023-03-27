package com.example.bankwithgraphql.Dto;

import com.example.bankwithgraphql.domain.Bank;
import com.example.bankwithgraphql.domain.Transaction;

public class BankAdapter {

    public static Bank getBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        if (bankDTO != null) {
            bank = new Bank(bankDTO.getAccountNumber(), bankDTO.getAccountHolder());
            for (Transaction transaction : bankDTO.getTransactionList()) {
                bank.addTransaction(transaction);
            }
        }
        return bank;
    }

    public static BankDTO getBankDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        if (bank != null) {
            bankDTO = new BankDTO(bank.getAccountNumber(), bank.getAccountHolder());
            for (Transaction transaction : bank.getTransactionList()) {
                bankDTO.addTransaction(transaction);
            }
        }

        return bankDTO;
    }
}
