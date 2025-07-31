package com.internetbanking.api.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Name {

	@NotNull
	@Max(value = 100)
	@NotBlank
	private String name;

	public Name(@NotNull @Max(100) @NotBlank String name) {
		super();
		this.name = name;
	}
}
