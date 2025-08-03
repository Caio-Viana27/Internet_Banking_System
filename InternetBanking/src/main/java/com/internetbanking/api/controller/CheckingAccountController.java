package com.internetbanking.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.api.service.CheckingAccountService;

@RestController
@RequestMapping("/account")
public class CheckingAccountController {

	private final CheckingAccountService service;

	public CheckingAccountController(CheckingAccountService service) {
		super();
		this.service = service;
	}
}
