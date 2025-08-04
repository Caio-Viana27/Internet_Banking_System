package com.internetbanking.api.controller;

import com.internetbanking.api.model.dto.BankingAccountDTO;
import com.internetbanking.api.model.dto.TransactionDTO;
import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.service.BankingAccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts") // Convenção REST: usar o plural
public class BankingAccountController {

	private final BankingAccountService service;

	public BankingAccountController(BankingAccountService service) {
		this.service = service;
	}

	@PostMapping("/deposit")
	public ResponseEntity<BankingAccountDTO> deposit(@Valid @RequestBody TransactionDTO transactionDTO) {
		BankingAccount updatedAccount = service.deposit(
				transactionDTO.accountNumber(),
				transactionDTO.amount()
		);
		return ResponseEntity.ok(new BankingAccountDTO(updatedAccount));
	}

	@PostMapping("/withdraw")
	public ResponseEntity<BankingAccountDTO> withdraw(@Valid @RequestBody TransactionDTO transactionDTO) {
		BankingAccount updatedAccount = service.withdraw(
				transactionDTO.accountNumber(),
				transactionDTO.amount()
		);
		return ResponseEntity.ok(new BankingAccountDTO(updatedAccount));
	}

	@PostMapping("/payment")
	public ResponseEntity<BankingAccountDTO> payment(@Valid @RequestBody TransactionDTO transactionDTO) {
		return ResponseEntity.status(501).build();
	}
}