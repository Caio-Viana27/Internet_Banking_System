package com.internetbanking.api.service;

import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.enums.TransactionType;
import com.internetbanking.api.repository.BankingAccountRepository;
import com.internetbanking.api.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExtratoService {

    private final TransactionRepository transactionRepository;
    private final BankingAccountRepository bankingAccountRepository;

    public ExtratoService(TransactionRepository transactionRepository, BankingAccountRepository bankingAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankingAccountRepository = bankingAccountRepository;
    }

    public List<Transaction> gerarExtrato(Integer accountNumber, TransactionType tipo, LocalDate dataInicio, LocalDate dataFim) {
        BankingAccount account = bankingAccountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new EntityNotFoundException("Conta não encontrada.");
        }

        List<Transaction> todasAsTransacoes = transactionRepository.findByAccountOrderByDataHoraDesc(account);

        // Prepara as datas para a comparação
        LocalDateTime startDateTime = (dataInicio != null) ? dataInicio.atStartOfDay() : null;
        LocalDateTime endDateTime = (dataFim != null) ? dataFim.atTime(LocalTime.MAX) : null;

        return todasAsTransacoes.stream()
                .filter(t -> tipo == null || t.getTipo().equals(tipo))
                .filter(t -> startDateTime == null || !t.getDataHora().isBefore(startDateTime))
                .filter(t -> endDateTime == null || !t.getDataHora().isAfter(endDateTime))
                .collect(Collectors.toList());
    }
}