package com.emailSender.service.api.model.dto;

import com.emailSender.service.api.model.entity.EmailEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDTO(
		Long id,
		@NotNull
		@NotBlank
		@Email
		String mailFrom,
		@NotNull
		@NotBlank
		@Email
		String mailTo,
		String mailSubject,
		String mailText
		) {

	public EmailDTO(EmailEntity email) {
		this(
			email.getId(),
			email.getMailFrom(),
			email.getMailTo(),
			email.getMailSubject(),
			email.getMailText()
			);
	}
}
