package com.internetbanking.api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internetbanking.api.model.dto.TransactionDTO;
import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.model.enums.TransactionType;
import com.internetbanking.api.repository.BankingAccountRepository;
import com.internetbanking.api.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {
	
	private final BankingAccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final UserService userService;
	private final EmailSenderService emailService;
	

	public TransactionService(BankingAccountRepository accountRepository, TransactionRepository transactionRepository, UserService userService, EmailSenderService emailService) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.userService = userService;
		this.emailService = emailService;
	}

	@Transactional
	public BankingAccount deposit(Integer accountNumber, BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
		}

		BankingAccount account = accountRepository.findByAccountNumber(accountNumber);
		if (account == null) {
			throw new EntityNotFoundException("Conta não encontrada.");
		}

		account.setBalance(account.getBalance().add(amount));
		Transaction transaction = new Transaction(TransactionType.DEPOSITO, amount, "Depósito em conta", account);
		transactionRepository.save(transaction);
		
		User user = userService.searchByAccountNumber(transaction.getAccount().getAccountNumber());
		emailService.sendDepositEmail(new TransactionDTO(transaction), user.getEmail());
		
		return account;
	}

	@Transactional
	public BankingAccount withdraw(Integer accountNumber, BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
		}

		BankingAccount account = accountRepository.findByAccountNumber(accountNumber);
		if (account == null) {
			throw new EntityNotFoundException("Conta não encontrada.");
		}

		if (account.getBalance().compareTo(amount) < 0) {
			throw new IllegalArgumentException("Saldo insuficiente para o saque.");
		}

		account.setBalance(account.getBalance().subtract(amount));
		Transaction transaction = new Transaction(TransactionType.SAQUE, amount, "Saque de conta", account);
		transactionRepository.save(transaction);
		
		User user = userService.searchByAccountNumber(transaction.getAccount().getAccountNumber());
		emailService.sendWithdrawEmail(new TransactionDTO(transaction), user.getEmail());
		
		return account;
	}
	
	@Transactional
	public BankingAccount payment(Integer accountNumber, BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
		}

		BankingAccount account = accountRepository.findByAccountNumber(accountNumber);
		if (account == null) {
			throw new EntityNotFoundException("Conta não encontrada.");
		}

		if (account.getBalance().compareTo(amount) < 0) {
			throw new IllegalArgumentException("Saldo insuficiente para o saque.");
		}
		account.setBalance(account.getBalance().subtract(amount));
		
		Transaction transaction = new Transaction(TransactionType.SAQUE, amount, "Saque de conta", account);
		transactionRepository.save(transaction);
		
		User user = userService.searchByAccountNumber(transaction.getAccount().getAccountNumber());
		emailService.sendPaymentEmail(new TransactionDTO(transaction), user.getEmail());
		
		return account;
	}
}
