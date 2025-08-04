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
    private final BankingAccountService bankingAccountService;
    private final PasswordEncoder passwordEncoder;
    // private final EmailService emailService;

    public UserService(UserRepository userRepository, BankingAccountService bankingAccountService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bankingAccountService = bankingAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    public User searchUserByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user == null) {
            throw new EntityNotFoundException("Usuário com CPF " + cpf + " não encontrado.");
        }
        return user;
    }

    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByCpf(userDTO.cpf()) != null) {
            throw new IllegalArgumentException("Usuário com este CPF já cadastrado");
        }
        if (userRepository.findByEmail(userDTO.email()) != null) {
            throw new IllegalArgumentException("Usuário com este e-mail já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(userDTO.password());

        User novoUsuario = new User(
                userDTO.name(),
                userDTO.cpf(),
                userDTO.email(),
                senhaCriptografada
        );

        User usuarioSalvo = userRepository.save(novoUsuario);

        bankingAccountService.createAccount(usuarioSalvo);

        // emailService.sendWelcomeEmail(usuarioSalvo);

        return usuarioSalvo;
    }
}