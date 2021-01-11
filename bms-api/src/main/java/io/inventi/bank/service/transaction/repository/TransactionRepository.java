package io.inventi.bank.service.transaction.repository;

import io.inventi.bank.service.transaction.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findAllByAccountId(Long id);

    List<AccountTransaction> findAllByOperationDateBetween(LocalDateTime localDateStart, LocalDateTime localDateEnd);

    List<AccountTransaction> findAllByAccountIdAndOperationDateBetween(Long id, LocalDateTime localDateStart, LocalDateTime localDateEnd);
}