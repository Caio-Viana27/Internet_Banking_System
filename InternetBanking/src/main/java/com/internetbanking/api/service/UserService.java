package com.internetbanking.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internetbanking.api.model.dto.UserDTO;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BankingAccountService bankingAccountService;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BankingAccountService bankingAccountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
		this.bankingAccountService = bankingAccountService;
        this.passwordEncoder = passwordEncoder;
    }

	public User searchUserByCpf(String cpf){
		return userRepository.findByCpf(cpf);
	}

	public User createUser(UserDTO userDTO) {
        if (userRepository.findByCpf(userDTO.cpf()) == null) {
            throw new IllegalArgumentException("Usuário com este CPF já cadastrado");
        }
        
        if (userRepository.findByEmail(userDTO.email()) == null) {
            throw new IllegalArgumentException("Usuário com este e-mail já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(userDTO.password());

        User novoUsuario = new User(
            userDTO.name(),
            userDTO.cpf(),
            userDTO.email(),
            senhaCriptografada
        );

        return userRepository.save(novoUsuario);
    }
}
