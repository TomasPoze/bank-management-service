package io.inventi.bank.service.transaction.controller;


import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public List<AccountTransaction> getTransactionsByAccountId(@PathVariable Long id) {
        return transactionService.getTransactionsByAccountId(id);
    }

    @GetMapping("/transactions")
    public List<AccountTransaction> getTransactions() {
        return transactionService.getTransactions();
    }
}