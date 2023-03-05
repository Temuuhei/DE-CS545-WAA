package backend_design.lab4.bank.service;

import backend_design.lab4.bank.data.BankRepository;
import backend_design.lab4.bank.domain.Bank;
import backend_design.lab4.bank.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    EmailSender emailSender;

    public void add (Bank bank) {
        bankRepository.save(bank);
        emailSender.sendEmail(String.valueOf(bank.getAccountNumber()), "Bank acc added");
    }

    public void update (Bank bank) {
        bankRepository.save(bank);
    }

    public Bank findByAccountNumber (Integer accNumber) {
        return bankRepository.findByAccountNumber(accNumber);
    }

    public void delete (Integer accNumber) {
        Bank bank = bankRepository.findByAccountNumber(accNumber);
        emailSender.sendEmail(String.valueOf(bank.getAccountNumber()), "Bank acc deleted");
        bankRepository.delete(accNumber);
    }

    public Collection<Bank> findAll () {
        return bankRepository.findAll();
    }
}
