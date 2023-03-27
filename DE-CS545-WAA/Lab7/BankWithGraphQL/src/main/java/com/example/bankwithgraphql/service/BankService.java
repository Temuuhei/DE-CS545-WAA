package com.example.bankwithgraphql.service;


import com.example.bankwithgraphql.Dto.BankAdapter;
import com.example.bankwithgraphql.Dto.BankDTO;
import com.example.bankwithgraphql.data.BankRepository;
import com.example.bankwithgraphql.domain.Bank;
import com.example.bankwithgraphql.domain.Transaction;
import com.example.bankwithgraphql.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    @Autowired
    EmailSender emailSender;

    public void add (BankDTO bankDTO) {
        Bank bank = BankAdapter.getBank(bankDTO);
        bankRepository.save(bank);
        emailSender.sendEmail(String.valueOf(bank.getAccountNumber()), "Bank acc added");
    }

    public void update (BankDTO bankDTO) {
        Bank bank = BankAdapter.getBank(bankDTO);
        bankRepository.save(bank);
    }

    public BankDTO findByAccountNumber (Integer accNumber) {
        Bank bank = bankRepository.findByAccountNumber(accNumber);
        return BankAdapter.getBankDTO(bank);
    }

    public BankDTO addTrans (Integer accNumber, double amount, String desc) {
        Bank bank = bankRepository.findByAccountNumber(accNumber);
        Transaction transaction = new Transaction(desc, amount);
        bank.addTransaction(transaction);
        System.out.println(bank.toString());
        System.out.println("Balance is : " + bank.calcBalance());
        return BankAdapter.getBankDTO(bank);
    }

    public void delete (Integer accNumber) {
        Bank bank = bankRepository.findByAccountNumber(accNumber);
        emailSender.sendEmail(String.valueOf(bank.getAccountNumber()), "Bank acc deleted");
        bankRepository.delete(bank);
    }

    public Collection<BankDTO> findAll() {
        Collection<Bank> banks = bankRepository.findAll();
        Collection<BankDTO> bankDTOS = new ArrayList<BankDTO>();
        for (Bank bank : banks) {
            bankDTOS.add(BankAdapter.getBankDTO(bank));
        }
        return bankDTOS;
    }

    public List<BankDTO> getAllBanks(int count) {
        Collection<Bank> banks = bankRepository.findAll();
        Collection<BankDTO> bankDTOS = new ArrayList<BankDTO>();
        for (Bank bank : banks) {
            bankDTOS.add(BankAdapter.getBankDTO(bank));
        }
        return bankDTOS.stream().collect(Collectors.toList()).subList(0, count);

    }
}
