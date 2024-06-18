package bankAccount.controller;


import bankAccount.model.Account;
import bankAccount.model.Transaction;
import bankAccount.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account CreateAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    ;

    @GetMapping("/{id}")
    public Account GetAccount(@PathVariable Long id) {
        return accountService.getAccount(id);

    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @PostMapping("/{id}/deposit")
    public void deposit(@PathVariable Long id, BigDecimal amount) {

        accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/widthdraw")
    public void withdraw(@PathVariable Long id, BigDecimal amount) {
        accountService.withdraw(id, amount);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getAllTransactions(@PathVariable Long accountId) {
        return accountService.getAllTransactions(accountId);

    }
}