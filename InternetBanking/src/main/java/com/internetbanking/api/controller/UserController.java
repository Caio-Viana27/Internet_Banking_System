package com.internetbanking.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
}
