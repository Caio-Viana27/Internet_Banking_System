package com.internetbanking.api.service;

import org.springframework.stereotype.Service;

import com.internetbanking.api.repository.CheckingAccountRepository;

@Service
public class CheckingAccountService {

	private final CheckingAccountRepository repository;

	public CheckingAccountService(CheckingAccountRepository repository) {
		super();
		this.repository = repository;
	}
}
