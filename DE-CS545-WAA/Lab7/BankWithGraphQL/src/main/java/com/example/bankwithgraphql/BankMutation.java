package com.example.bankwithgraphql;


import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.bankwithgraphql.Dto.BankAdapter;
import com.example.bankwithgraphql.Dto.BankDTO;
import com.example.bankwithgraphql.domain.Bank;
import com.example.bankwithgraphql.domain.Transaction;
import com.example.bankwithgraphql.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankMutation implements GraphQLMutationResolver {

    @Autowired
    private BankService bankService;

    public BankDTO createBank (Integer accNumber, String name) {
        BankDTO bankDTO = new BankDTO(accNumber, name);
        bankService.add(bankDTO);
        return bankDTO;
    }

    public void update (Integer accNumber, String name) {
        BankDTO bankDTO = new BankDTO(accNumber, name);
        bankService.update(bankDTO);

    }

    public BankDTO deleteBank(Integer accNumber) {
        BankDTO bank = bankService.findByAccountNumber(accNumber);
        bankService.delete(accNumber);
        return bank;
    }

    public BankDTO createTransaction(final int accountNumber, final String accountHolder, final double amount, final String desc) {

        return bankService.addTrans(accountNumber, amount, desc);
    }

}
