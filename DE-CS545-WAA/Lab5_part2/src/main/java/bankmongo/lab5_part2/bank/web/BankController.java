package bankmongo.lab5_part2.bank.web;

import bankmongo.lab5_part2.bank.DTO.BankAdapter;
import bankmongo.lab5_part2.bank.DTO.BankDTO;
import bankmongo.lab5_part2.bank.domain.Bank;
import bankmongo.lab5_part2.bank.domain.Transaction;
import bankmongo.lab5_part2.bank.service.BankService;
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
    public ResponseEntity<?> createAccount(@RequestBody BankDTO bankDTO) {
        if (bankDTO.getAccountNumber() == null || bankDTO.getAccountHolder() == null) {
            return new ResponseEntity<>(new CustomerErrorType("Account number or Account holder is not null"), HttpStatus.FORBIDDEN);
        }

        if (bankService.findAll().contains(bankDTO.getAccountNumber())) {
            return new ResponseEntity<>(new CustomerErrorType("Account number is duplicated!"), HttpStatus.FORBIDDEN);
        }
        bankService.add(bankDTO);
        return new ResponseEntity<BankDTO>(bankDTO, HttpStatus.OK);
    }

    @GetMapping("/deposit/{accountNumber}/{amount}")
    public ResponseEntity<?> deposit(@PathVariable int accountNumber, @PathVariable Double amount){
        if(amount < 1)
            return new ResponseEntity(new CustomerErrorType("Deposit amount must be bigger than 0"), HttpStatus.FORBIDDEN);
        BankDTO bankDTO = bankService.findByAccountNumber(accountNumber);
        if(bankDTO != null) {
            Transaction transaction = new Transaction("deposit",amount);
            bankDTO.addTransaction(transaction);
            bankService.add(bankDTO);
            return new ResponseEntity<BankDTO>(bankDTO, HttpStatus.OK);
        }
        return new ResponseEntity(new CustomerErrorType("Bank account doesn't exists"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/withdraw/{accountNumber}/{amount}")
    public ResponseEntity<?> withdraw(@PathVariable int accountNumber,@PathVariable double amount){
        if(amount < 1)
            return new ResponseEntity(new CustomerErrorType("Withdraw amount must be bigger than 0"), HttpStatus.FORBIDDEN);
        BankDTO bankDTO = bankService.findByAccountNumber(accountNumber);
        if(bankDTO != null) {

            if(bankDTO.calcBalance() - amount < 0)
                return new ResponseEntity(new CustomerErrorType("Insufficient balance"), HttpStatus.FORBIDDEN);

            Transaction transaction = new Transaction("withdraw",-amount);
            bankDTO.addTransaction(transaction);
            bankService.add(bankDTO);
            return new ResponseEntity<BankDTO>(bankDTO, HttpStatus.OK);
        }
        return new ResponseEntity(new CustomerErrorType("Bank account doesn't exists"), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getAccount(@PathVariable int accountNumber){
        BankDTO bankDTO= bankService.findByAccountNumber(accountNumber);
        System.out.println("here" + bankDTO.toString());
        if(bankDTO == null) {
            return new ResponseEntity(new CustomerErrorType("Bank account doesn't exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bankDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<BankDTO>> getAllBanks(){
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
