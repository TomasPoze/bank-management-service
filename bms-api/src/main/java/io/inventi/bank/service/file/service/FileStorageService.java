package io.inventi.bank.service.file.service;

import io.inventi.bank.service.account.entity.Account;
import io.inventi.bank.service.account.entity.AccountCurrency;
import io.inventi.bank.service.account.service.AccountService;
import io.inventi.bank.service.file.exception.FileStorageException;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import io.inventi.bank.service.transaction.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class FileStorageService {
    private final Path storageLocation;
    private final TransactionService transactionService;
    private final AccountService accountService;

    public FileStorageService(TransactionService transactionService, AccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.storageLocation = Paths.get("./storage").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.storageLocation);
        } catch (IOException e) {
            throw new FileStorageException("Unable to create file storage");
        }
    }

    public void storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            throw new FileStorageException("File name is invalid");
        }

        Path targetLocation = this.storageLocation.resolve(fileName);

        try {
            BufferedReader br = Files.newBufferedReader(targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            String data;
            while ((data = br.readLine()) != null) {
                String[] value = data.split(",");
                try {
                    AccountTransaction accountTransaction = new AccountTransaction();
                    accountTransaction.setAccountNumber(value[0]);
                    accountTransaction.setOperationDate(LocalDateTime.parse(value[1]));
                    accountTransaction.setBeneficiary(value[2]);
                    accountTransaction.setComment(value[3]);
                    accountTransaction.setAmount(new BigDecimal(value[4]));
                    accountTransaction.setCurrency(AccountCurrency.valueOf(value[5]));
                    Account account = accountService.findAccountByAccountNumber(accountTransaction.getAccountNumber());
                    accountTransaction.setAccount(account);
                    transactionService.createTransaction(accountTransaction);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file");
        }
    }
}