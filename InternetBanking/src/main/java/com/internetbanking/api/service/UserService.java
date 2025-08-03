package com.internetbanking.api.service;

import org.springframework.stereotype.Service;

import com.internetbanking.api.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}
}
