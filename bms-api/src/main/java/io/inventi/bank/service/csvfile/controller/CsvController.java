package io.inventi.bank.service.csvfile.controller;

import io.inventi.bank.service.csvfile.util.WriteCsvToResponse;
import io.inventi.bank.service.file.service.FileStorageService;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.service.TransactionService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLConnection;
import java.util.List;


@RestController
@RequestMapping("/csv")
public class CsvController {

    private final TransactionService transactionService;
    private final FileStorageService fileStorageService;

    public CsvController(TransactionService transactionService, FileStorageService fileStorageService) {
        this.transactionService = transactionService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(value = "/download", produces = "text/csv")
    public void createCsv(@RequestParam String localDateStart,
                          @RequestParam String localDateEnd,
                          HttpServletResponse response
    ) {
        try {
            List<AccountTransaction> accountTransactions = transactionService.getTransactionsByDate(localDateStart, localDateEnd);
            WriteCsvToResponse.writeReports(response.getWriter(), accountTransactions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public void uploadCsv(@RequestParam(name = "file") MultipartFile file) {
        fileStorageService.storeFile(file);
    }

}
