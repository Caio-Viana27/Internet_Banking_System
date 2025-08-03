package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.Balance;
import com.internetbanking.api.model.entity.CheckingAccount;

import jakarta.validation.constraints.NotNull;

public record CheckingAccountDTO(
		@NotNull Integer accountNumber,
		@NotNull Integer agencyNumber,
		@NotNull Balance balance) {

	
	public CheckingAccountDTO(@NotNull CheckingAccount account) {
		this(account.getAccountNumber(), account.getAgencyNumber(), account.getBalance());
	}
}
