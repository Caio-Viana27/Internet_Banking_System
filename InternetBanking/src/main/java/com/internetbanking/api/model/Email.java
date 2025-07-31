package com.internetbanking.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Email {

	@NotNull
	@NotBlank
	private String cpf;

	public Email(@NotNull @NotBlank String cpf) {
		super();
		this.cpf = cpf;
	}
}
