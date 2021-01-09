package io.inventi.bank.service.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.inventi.bank.service.transaction.entity.AccountTransaction;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.CurrencyType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "user")
    private String user;

    @Column(name = "total_balance")
    private BigDecimal totalBalance;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;

    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> accountTransactions = new ArrayList<>();
}
