package com.internetbanking.api.controller;

import com.internetbanking.api.model.dto.TransactionDTO; // Você precisaria de um DTO para o extrato
import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.enums.TransactionType;
import com.internetbanking.api.service.ExtratoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/extrato")
public class ExtratoController {

    private final ExtratoService extratoService;

    public ExtratoController(ExtratoService extratoService) {
        this.extratoService = extratoService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionDTO>> gerarExtrato(
            @PathVariable Integer accountNumber,
            @RequestParam(required = false) TransactionType tipo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        List<Transaction> transactions = extratoService.gerarExtrato(accountNumber, tipo, dataInicio, dataFim);

        List<TransactionDTO> transactionDTOs = transactions.stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(transactionDTOs);
    }
}