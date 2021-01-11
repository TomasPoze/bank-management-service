package io.inventi.bank.service.transaction.service;

import io.inventi.bank.service.account.entity.Account;
import io.inventi.bank.service.account.service.AccountService;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public List<AccountTransaction> getTransactionsByAccountId(Long id) {
        return transactionRepository.findAllByAccountId(id);
    }

    public List<AccountTransaction> getTransactionsByDate(String localDateStart, String localDateEnd) {
        String dateStartString = localDateStart + "T00:00";
        LocalDateTime dateTimeStart = LocalDateTime.parse(dateStartString);
        String dateEndString = localDateEnd + "T23:59";
        LocalDateTime dateTimeEnd = LocalDateTime.parse(dateEndString);
        return transactionRepository.findAllByOperationDateBetween(dateTimeStart, dateTimeEnd);
    }

    public List<AccountTransaction> getTransactionsByDateAndAccountNumber(Long id, String localDateStart, String localDateEnd) {
        String dateStartString = localDateStart + "T00:00";
        LocalDateTime dateTimeStart = LocalDateTime.parse(dateStartString);
        String dateEndString = localDateEnd + "T23:59";
        LocalDateTime dateTimeEnd = LocalDateTime.parse(dateEndString);
        return transactionRepository.findAllByAccountIdAndOperationDateBetween(id, dateTimeStart, dateTimeEnd);
    }

    public List<AccountTransaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public void createTransaction(AccountTransaction accountTransaction) {
        transactionRepository.save(accountTransaction);
    }

    public String calculateBalance(String dateStart, String dateEnd, String accountNumber) {
        Account account = accountService.findAccountByAccountNumber(accountNumber);
        List<AccountTransaction> allTransactions = getTransactionsByAccountId(account.getId());

        for (AccountTransaction transaction : allTransactions) {
            if (transaction.getBalance() == null) {
                transaction.setBalance(account.getTotalBalance().subtract(transaction.getAmount()));
                account.setTotalBalance(new BigDecimal(String.valueOf(transaction.getBalance())));
                transactionRepository.save(transaction);
            }
        }
        if (dateStart.equals("")) {
            dateStart = allTransactions.get(0).getOperationDate().toString().substring(0, 10);
        }
        if (dateEnd.equals("")) {
            dateEnd = allTransactions.get(allTransactions.size() - 1).getOperationDate().toString().substring(0, 10);
        }
        List<AccountTransaction> transactionList = getTransactionsByDateAndAccountNumber(account.getId(), dateStart, dateEnd);

        return transactionList.get(transactionList.size() - 1).getBalance().toString();
    }
}