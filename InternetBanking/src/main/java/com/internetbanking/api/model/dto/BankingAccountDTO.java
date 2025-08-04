package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.entity.BankingAccount;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record BankingAccountDTO(
		@NotNull
		Integer accountNumber,

		@NotNull
		Integer agencyNumber,

		@NotNull
		@PositiveOrZero(message = "O saldo não pode ser negativo")
		BigDecimal balance
) {

	public BankingAccountDTO(@NotNull BankingAccount account) {
		// PONTO 2: A conversão agora funciona, pois account.getBalance() retorna BigDecimal
		this(account.getAccountNumber(), account.getAgencyNumber(), account.getBalance());
	}
}