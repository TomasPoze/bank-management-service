package io.inventi.bank.service.csvfile.controller;

import io.inventi.bank.service.csvfile.util.WriteCsvToResponse;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/csv")
public class CsvController {

    private final TransactionService transactionService;

    public CsvController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/download", produces = "text/csv")
    public void createCsv(@RequestParam String localDateStart,
                          @RequestParam String localDateEnd,
                          HttpServletResponse response
    ) {
        try {
            List<AccountTransaction> accountTransactions = transactionService.getTransactionsByDate(localDateStart,localDateEnd);
            WriteCsvToResponse.writeReports(response.getWriter(),accountTransactions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
