package io.inventi.bank.service.transaction.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.inventi.bank.service.account.entity.Account;
import io.inventi.bank.service.account.entity.AccountCurrency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "AccountTransactions")
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"accountTransactions"})
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Account account;

    @Column(name = "account_number")
    private String account_numer;

    @Column(name = "operation_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime operationDate;

    @Column(name = "beneficiary")
    private String beneficiary;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private AccountCurrency currency;

}
