package io.inventi.bank.service.account.service;

import io.inventi.bank.service.account.entity.Account;
import io.inventi.bank.service.account.entity.AccountCurrency;
import io.inventi.bank.service.account.exception.AccountNotFoundException;
import io.inventi.bank.service.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account was not found by this: " + id + " id."));
    }

    public Account findAccountByAccountNumber(String accountNumber) {
        if (accountRepository.findByAccountNumber(accountNumber) == null) {
            return createAccount(accountNumber);
        } else {
            return accountRepository.findByAccountNumber(accountNumber);
        }
    }

    public Account createAccount(String accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setCurrency(AccountCurrency.EURO);
        account.setTotalBalance(BigDecimal.valueOf(1000));
        account.setUser("Jone Jones");
        accountRepository.save(account);
        return account;
    }
}
