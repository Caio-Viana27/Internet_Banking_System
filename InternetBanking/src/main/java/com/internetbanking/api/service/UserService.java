package com.internetbanking.api.service;

import com.internetbanking.api.model.dto.UserDTO;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BankingAccountService bankingAccountService;

    public UserService(UserRepository userRepository, BankingAccountService bankingAccountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bankingAccountService = bankingAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    public User searchUserByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user == null) {
            throw new EntityNotFoundException("User's CPF not found: " + cpf);
        }
        return user;
    }
    
    public User searchByAccountNumber(Integer accountNumber) {
        User user = (bankingAccountService.searchBankAccountByAccountNumber(accountNumber)).getUser();
        
        if (user == null) {
            throw new EntityNotFoundException("User not found for AccountNumb: " + accountNumber);
        }
        return user;
    }

    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByCpf(userDTO.cpf()) != null) {
            throw new IllegalArgumentException("this cpf: " + userDTO.cpf() + " belongs to another account, maybe try login");
        }
        if (userRepository.findByEmail(userDTO.email()) != null) {
            throw new IllegalArgumentException("this email: " + userDTO.email() + " is already registered");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.password());

        User newUser = new User(
                userDTO.name(),
                userDTO.cpf(),
                userDTO.email(),
                encodedPassword
        );
        User savedUser = userRepository.save(newUser);
        bankingAccountService.createAccount(savedUser);
        return savedUser;
    }
}