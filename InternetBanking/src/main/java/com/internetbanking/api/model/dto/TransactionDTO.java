package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        TransactionType tipo,
        BigDecimal valor,
        String descricao,
        LocalDateTime dataHora
) {
        public TransactionDTO(Transaction transaction) {
                this(
                        transaction.getTipo(),
                        transaction.getValor(),
                        transaction.getDescricao(),
                        transaction.getDataHora()
                );
        }
}