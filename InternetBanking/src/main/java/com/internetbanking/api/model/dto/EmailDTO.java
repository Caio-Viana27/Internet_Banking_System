package com.internetbanking.api.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(
		String mailFrom,
		@NotNull
		@NotBlank
		@Email
		String mailTo,
		String mailSubject,
		String mailText
		) {
}