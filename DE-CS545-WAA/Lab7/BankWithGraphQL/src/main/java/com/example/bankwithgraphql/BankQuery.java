package com.example.bankwithgraphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.bankwithgraphql.Dto.BankAdapter;
import com.example.bankwithgraphql.Dto.BankDTO;
import com.example.bankwithgraphql.domain.Bank;
import com.example.bankwithgraphql.domain.Transaction;
import com.example.bankwithgraphql.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class BankQuery implements GraphQLQueryResolver {

    @Autowired
    private BankService bankService;

    public List<BankDTO> getBanks(final int count) {
        return bankService.getAllBanks(count);
    }

    public BankDTO getBank(final int accNumber) {
        return bankService.findByAccountNumber(accNumber);
    }


//    public BankDTO addTransaction(final int accNumber, final double amount, final String desc) {
//        Transaction transaction = new Transaction(desc, amount);
//        Bank bank = BankAdapter.getBank(bankService.findByAccountNumber(accNumber));
//        bank.addTransaction(transaction);
//        return bankService.findByAccountNumber(accNumber);
//    }
}
