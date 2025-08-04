package com.internetbanking.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Email {

	@NotNull
	@NotBlank
	private String email;

	public Email(@NotNull @NotBlank String email) {
		super();
		this.email = email;
	}

	@Override
	public String toString() {
		return email;
	}
}
