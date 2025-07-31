package com.internetbanking.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Password {

	@NotNull
	@NotBlank
	private String password;

	public Password(@NotNull @NotBlank String password) {
		super();
		this.password = password;
	}
}
