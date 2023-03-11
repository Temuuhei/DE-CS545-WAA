package bankmongo.lab5_part2.bank.service;

import bankmongo.lab5_part2.bank.DTO.BankAdapter;
import bankmongo.lab5_part2.bank.DTO.BankDTO;
import bankmongo.lab5_part2.bank.data.BankRepository;
import bankmongo.lab5_part2.bank.domain.Bank;
import bankmongo.lab5_part2.bank.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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


}
