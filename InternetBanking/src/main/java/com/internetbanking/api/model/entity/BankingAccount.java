package com.internetbanking.api.model.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.internetbanking.api.model.Balance;
import com.internetbanking.api.model.dto.BankingAccountDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity(name = "banking_account")
public class BankingAccount {
	
	@Id
	@NotNull
	private Integer accountNumber;
	
	@NotNull
	private Integer agencyNumber;
	
	@NotNull
	@JdbcTypeCode(SqlTypes.DECIMAL)
	private Balance balance;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	private User user;

	public BankingAccount(
			@NotNull Integer accountNumber,
			@NotNull Integer agencyNumber,
			@NotNull Balance balance) {
		
		super();
		this.accountNumber = accountNumber;
		this.agencyNumber = agencyNumber;
		this.balance = balance;
	}
	
	public BankingAccount(@NotNull BankingAccountDTO dto) {
		super();
		this.accountNumber = dto.accountNumber();
		this.agencyNumber = dto.agencyNumber();
		this.balance = dto.balance();
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

	public Balance getBalance() {
		return balance;
	}

	public void setBalance(Balance balance) {
		this.balance = balance;
	}
}
