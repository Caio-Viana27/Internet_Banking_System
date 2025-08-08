package com.internetbanking.api.service;

import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.repository.BankingAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class BankingAccountService {

	private final BankingAccountRepository accountRepository;
	private final EmailSenderService emailService;

	public BankingAccountService(BankingAccountRepository accountRepository, EmailSenderService emailService) {
		this.accountRepository = accountRepository;
		this.emailService = emailService;
	}

	@Transactional
	public BankingAccount createAccount(User user) {
		BankingAccount newAccount = new BankingAccount();
		
		newAccount.setUser(user);
		newAccount.setAgencyNumber(1);
		newAccount.setBalance(BigDecimal.ZERO);
		
		emailService.sendWelcomeEmail(user);
		
		return accountRepository.save(newAccount);
	}
	
	public BankingAccount searchBankAccountByAccountNumber(Integer accountNumber) {
		BankingAccount account = accountRepository.findByAccountNumber(accountNumber);
		
		if (account == null) {
			throw new EntityNotFoundException("Account not found!");
		}
		
		return account;
	}
}