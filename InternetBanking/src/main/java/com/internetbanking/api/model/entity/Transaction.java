package com.internetbanking.api.model.entity;

import com.internetbanking.api.model.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions") // Nome da tabela no banco
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType tipo;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataHora;

    private String descricao;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id")
    private BankingAccount account;

    // Construtor vazio para o JPA
    public Transaction() {
    }

    // Construtor para facilitar a criação
    public Transaction(TransactionType tipo, BigDecimal valor, String descricao, BankingAccount account) {
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.account = account;
        this.dataHora = LocalDateTime.now();
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTipo() {
        return tipo;
    }

    public void setTipo(TransactionType tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BankingAccount getAccount() {
        return account;
    }

    public void setAccount(BankingAccount account) {
        this.account = account;
    }
}