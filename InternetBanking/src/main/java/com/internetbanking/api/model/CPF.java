package com.internetbanking.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CPF {
	
	@NotNull
	@NotBlank
	@Size(max = 11, min = 11)
	private String cpf;

	public CPF(@NotNull @NotBlank @Size(max = 11, min = 11) String cpf) {
		super();
		this.cpf = cpf;
	}
}
