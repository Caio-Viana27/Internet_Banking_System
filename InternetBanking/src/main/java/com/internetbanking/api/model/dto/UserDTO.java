package com.internetbanking.api.model.dto;

import java.util.List;

import com.internetbanking.api.model.CPF;
import com.internetbanking.api.model.Email;
import com.internetbanking.api.model.Name;
import com.internetbanking.api.model.Password;
import com.internetbanking.api.model.entity.CheckingAccount;
import com.internetbanking.api.model.entity.User;

import jakarta.validation.constraints.NotNull;

public record UserDTO(
		         Long id,
		@NotNull Name name,
		@NotNull CPF cpf,
		@NotNull Email email,
		@NotNull Password password,
		@NotNull List<CheckingAccount> accounts) {
	
	public UserDTO(@NotNull User user) {
		this(user.getId(), user.getName(), user.getCpf(), user.getEmail(), new Password(user.getPassword()), user.getAccounts());
	}

}
