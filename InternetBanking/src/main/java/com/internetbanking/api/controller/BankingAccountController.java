package com.internetbanking.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.api.service.BankingAccountService;

@RestController
@RequestMapping("/account")
public class BankingAccountController {

	private final BankingAccountService service;

	public BankingAccountController(BankingAccountService service) {
		super();
		this.service = service;
	}
}
