package com.internetbanking.api.controller;

import com.internetbanking.api.model.dto.UserDTO;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		User newUser = service.createUser(userDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();

		return ResponseEntity.created(location).body(new UserDTO(newUser));
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<UserDTO> searchByCpf(@PathVariable String cpf) {
		User user = service.searchUserByCpf(cpf);

		return ResponseEntity.ok(new UserDTO(user));
	}
}