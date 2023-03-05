package backend_design.lab4.bank.web;

import backend_design.lab4.bank.domain.Bank;
import backend_design.lab4.bank.domain.Transaction;
import backend_design.lab4.bank.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@AllArgsConstructor
@RequestMapping("/banks")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Bank bank) {
        if (bank.getAccountNumber() == null || bank.getAccountHolder() == null) {
            return new ResponseEntity<>(new CustomerErrorType("Account number or Account holder is not null"), HttpStatus.FORBIDDEN);
        }

        if (bankService.findAll().contains(bank.getAccountNumber())) {
            return new ResponseEntity<>(new CustomerErrorType("Account number is duplicated!"), HttpStatus.FORBIDDEN);
        }
        bankService.add(bank);
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @GetMapping("/deposit/{accountNumber}/{amount}")
    public ResponseEntity<?> deposit(@PathVariable int accountNumber, @PathVariable Double amount){
        if(amount < 1)
            return new ResponseEntity(new CustomerErrorType("Deposit amount must be bigger than 0"), HttpStatus.FORBIDDEN);
        Bank bank = bankService.findByAccountNumber(accountNumber);
        if(bank != null) {
            Transaction transaction = new Transaction("deposit",amount);
            bank.addTransaction(transaction);
            bankService.add(bank);
            return new ResponseEntity(bank, HttpStatus.OK);
        }
        return new ResponseEntity(new CustomerErrorType("Bank account doesn't exists"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/withdraw/{accountNumber}/{amount}")
    public ResponseEntity<?> withdraw(@PathVariable int accountNumber,@PathVariable double amount){
        if(amount < 1)
            return new ResponseEntity(new CustomerErrorType("Withdraw amount must be bigger than 0"), HttpStatus.FORBIDDEN);
        Bank bank = bankService.findByAccountNumber(accountNumber);
        if(bank != null) {

            if(bank.calcBalance() - amount < 0)
                return new ResponseEntity(new CustomerErrorType("Insufficient balance"), HttpStatus.FORBIDDEN);

            Transaction transaction = new Transaction("withdraw",-amount);
            bank.addTransaction(transaction);
            bankService.add(bank);
            return new ResponseEntity(bank, HttpStatus.OK);
        }
        return new ResponseEntity(new CustomerErrorType("Bank account doesn't exists"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable int accountNumber){
        Bank bank = bankService.findByAccountNumber(accountNumber);
        if(bank == null) {
            return new ResponseEntity(new CustomerErrorType("Bank account doesn't exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bank, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<Bank>> getAllBanks(){
        return new ResponseEntity<>(bankService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<?> removeAccount(@PathVariable int accountNumber){
        if(bankService.findAll().contains(accountNumber)) {
            bankService.delete(accountNumber);
            return new ResponseEntity("Account number: "+accountNumber + " deleted", HttpStatus.OK);
        }
        return new ResponseEntity(new CustomerErrorType("Bank account doesn't exist"), HttpStatus.NOT_FOUND);
    }
}
