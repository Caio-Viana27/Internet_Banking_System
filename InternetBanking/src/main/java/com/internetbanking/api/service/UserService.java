package com.internetbanking.api.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internetbanking.api.model.dto.UserDTO;
import com.internetbanking.api.model.entity.User;
import com.internetbanking.api.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	public Optional<UserDetails> buscarUserByCpf(String cpf){
		return userRepository.findByCpf(cpf);
	}

	public User cadastrarUser(UserDTO userDTO) {
        if (userRepository.findByCpf(userDTO.cpf()).isPresent()) {
            throw new IllegalArgumentException("Usuário com este CPF já cadastrado");
        }
        
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
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
