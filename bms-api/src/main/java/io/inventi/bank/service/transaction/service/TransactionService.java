package io.inventi.bank.service.transaction.service;

import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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

    public List<AccountTransaction> getTransactions() {
        return transactionRepository.findAll();
    }
}
