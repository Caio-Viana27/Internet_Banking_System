package com.internetbanking.api.model.entity;

import com.internetbanking.api.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull
    private LocalDateTime dateTime;

    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private BankingAccount account;

    public Transaction() {}

    public Transaction(TransactionType transactionType, BigDecimal amount, String description, BankingAccount account) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal valor) {
        this.amount = valor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDataHora(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BankingAccount getAccount() {
        return account;
    }

    public void setAccount(BankingAccount account) {
        this.account = account;
    }
}