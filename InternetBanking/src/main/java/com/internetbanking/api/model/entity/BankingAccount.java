package com.internetbanking.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class BankingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private Integer accountNumber;

	@NotNull
	private Integer agencyNumber;

	@NotNull
	@Column(precision = 10, scale = 2)
	private BigDecimal balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public BankingAccount() {
	}

	// Construtor principal para criar contas válidas
	public BankingAccount(
			@NotNull Integer accountNumber,
			@NotNull Integer agencyNumber,
			@NotNull BigDecimal balance,
			@NotNull User user) {

		this.accountNumber = accountNumber;
		this.agencyNumber = agencyNumber;
		this.balance = balance;
		this.user = user;
	}

	// Getters e Setters para todos os campos

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(Integer agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}