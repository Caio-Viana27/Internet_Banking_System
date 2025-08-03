package com.internetbanking.api.service;

import org.springframework.stereotype.Service;

import com.internetbanking.api.repository.BankingAccountRepository;

@Service
public class BankingAccountService {

	private final BankingAccountRepository repository;

	public BankingAccountService(BankingAccountRepository repository) {
		super();
		this.repository = repository;
	}
}
