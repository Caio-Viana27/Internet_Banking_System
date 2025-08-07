package com.emailSender.service.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailSender.service.api.model.dto.EmailDTO;
import com.emailSender.service.api.model.entity.EmailEntity;
import com.emailSender.service.api.service.EmailService;

@RestController
@RequestMapping("/send")
public class EmailController {
	
	private EmailService service;

	public EmailController(EmailService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<EmailEntity> sendEmail(@RequestBody EmailDTO data) {
		return new ResponseEntity<EmailEntity>(service.sendEmail(data), HttpStatus.CREATED);
	}
}
