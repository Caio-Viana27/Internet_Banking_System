package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.Balance;
import com.internetbanking.api.model.entity.BankingAccount;

import jakarta.validation.constraints.NotNull;

public record BankingAccountDTO(
		@NotNull Integer accountNumber,
		@NotNull Integer agencyNumber,
		@NotNull Balance balance) {

	
	public BankingAccountDTO(@NotNull BankingAccount account) {
		this(account.getAccountNumber(), account.getAgencyNumber(), account.getBalance());
	}
}
