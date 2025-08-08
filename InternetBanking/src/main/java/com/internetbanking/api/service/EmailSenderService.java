package com.internetbanking.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.internetbanking.api.model.dto.EmailDTO;
import com.internetbanking.api.model.dto.TransactionDTO;
import com.internetbanking.api.model.entity.User;

@Service
public class EmailSenderService {
	
	private WebClient webClient;
	
	private String clientEmail = "caio.vtc27@gmail.com";
	private final String uriEmailServer = "http://localhost:8082/emailsenderservice/send";
	
	public EmailSenderService(WebClient webClient) {
		super();
		this.webClient = webClient;
	}

	public void sendWelcomeEmail(User newUser) {
		
		String subject = "Welcome to Internet Banking";
		String text = "Hey, thanks for signing up to Internet Banking";
		
		EmailDTO welcomeEmail = new EmailDTO(clientEmail, newUser.getEmail(), subject, text);
		
		this.send(welcomeEmail);
	}
	
	public void sendDepositEmail(TransactionDTO transaction, String userEmail) {
		
		String subject = "Deposit notification";
		String text = "Hey, your deposit was completed" + "\n"
					+ "value: " + transaction.amount() + "\n"
					+ transaction.description();
		
		EmailDTO depositEmail = new EmailDTO(clientEmail, userEmail, subject, text);
		
		this.send(depositEmail);
	}
	
	public void sendWithdrawEmail(TransactionDTO transaction, String userEmail) {
		
		String subject = "Withdraw notification";
		String text = "Hey, a withdraw was made from your account" + "\n"
					+ "value: " + transaction.amount() + "\n"
					+ transaction.description();
		
		EmailDTO withdrawEmail = new EmailDTO(clientEmail, userEmail, subject, text);
		
		this.send(withdrawEmail);
	}
	
	public void sendPaymentEmail(TransactionDTO transaction, String userEmail) {
		
		String subject = "Payment notfication";
		String text = "Hey, your new payment was processed" + "\n"
					+ "value: " + transaction.amount() + "\n"
					+ transaction.description();
		
		EmailDTO paymentEmail = new EmailDTO(clientEmail, userEmail, subject, text);
		
		this.send(paymentEmail);
	}
	
	private void send(EmailDTO email) {
		webClient.post()
        .uri(uriEmailServer)
        .bodyValue(email)
        .retrieve()
        .bodyToMono(Void.class);
	}
}
