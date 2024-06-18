package bankAccount.service;


import bankAccount.model.Account;
import bankAccount.model.Transaction;
import bankAccount.model.TransactionType;
import bankAccount.repository.AccountRepository;
import bankAccount.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    public Account createAccount(Account account) {

        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {

        return accountRepository.findById(id).orElse(null);

    }


    public BigDecimal getBalance(Long id) {
        Account account = getAccount(id);
        return account.getBalance();

    }


    public void deposit(Long id, BigDecimal amount) {

        Account account = getAccount(id);

        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);


            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setType(TransactionType.CREDIT);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }
    }

    public void withdraw(Long id, BigDecimal amount){
        Account account = getAccount(id);

        if(account!=null && account.getBalance().compareTo(amount) >= 0){
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);


            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setType(TransactionType.DEBIT);
            transaction.setAmount(amount);
            transaction.setDate(LocalDateTime.now());
            transactionRepository.save(transaction);
        }

    }

    public void delete(Long id) {
    accountRepository.deleteById(id);

    }
    public List<Transaction> getAllTransactions(Long accountId) {

        Account account = getAccount(accountId);
        if (account != null) {
            return transactionRepository.findByAccount(account);
        } else {
            return Collections.emptyList();
        }
    }
}

