package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        TransactionType transactionType,
        BigDecimal amount,
        String description,
        LocalDateTime dateTime,
        Integer accountNumber
) {
        public TransactionDTO(Transaction transaction) {
                this(
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        transaction.getDescription(),
                        transaction.getDateTime(),
                        transaction.getAccount().getAccountNumber()
                );
        }
}