package backend_design.lab4.bank.data;

import backend_design.lab4.bank.domain.Bank;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {
    private Map<Integer, Bank> datas = new HashMap<>();

    public void save (Bank bank) {
        datas.put(bank.getAccountNumber(), bank);
    }

    public Bank findByAccountNumber(Integer accNumber) {
        return datas.get(accNumber);
    }

    public void delete (Integer accNumber) {
        datas.remove(accNumber);
    }

    public Collection<Bank> findAll( ) {
        return datas.values();
    }
}
