package com.internetbanking.api.service;

import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.model.enums.TransactionType;
import com.internetbanking.api.repository.BankingAccountRepository;
import com.internetbanking.api.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class BankingAccountService {

	private final BankingAccountRepository accountRepository;
	private final TransactionRepository transactionRepository;

	public BankingAccountService(BankingAccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Transactional
	public BankingAccount createAccount(User user) {
		BankingAccount newAccount = new BankingAccount();
		newAccount.setUser(user);
		newAccount.setAgencyNumber(1);
		newAccount.setAccountNumber(10000 + new Random().nextInt(90000));
		newAccount.setBalance(BigDecimal.ZERO);
		return accountRepository.save(newAccount);
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
		return account;
	}

	@Transactional
	public BankingAccount withdraw(Integer accountNumber, BigDecimal amount) {
		if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
		}

		// AÇÃO NECESSÁRIA 2: Lógica alterada para tratar 'null'
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
		return account;
	}
}