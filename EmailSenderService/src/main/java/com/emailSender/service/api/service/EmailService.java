package com.emailSender.service.api.service;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.emailSender.service.api.model.dto.EmailDTO;
import com.emailSender.service.api.model.entity.EmailEntity;
import com.emailSender.service.api.model.enums.EmailStatus;
import com.emailSender.service.api.repository.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
	
	private EmailRepository repository;

	private JavaMailSender emailSender;
	
	public EmailService(EmailRepository repository, JavaMailSender emailSender) {
		super();
		this.repository = repository;
		this.emailSender = emailSender;
	}

	@Transactional
	public EmailEntity sendEmail(EmailDTO dto) {
		EmailEntity data = new EmailEntity(dto);
		
		data.setSendDateEmail(LocalDateTime.now());
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(dto.mailFrom());
		message.setTo(dto.mailTo());
		message.setSubject(dto.mailSubject());
		message.setText(dto.mailText());
		
		data.setStatus(EmailStatus.SENT);
		
		emailSender.send(message);
		
		repository.save(data);
		return data;
	}
}
