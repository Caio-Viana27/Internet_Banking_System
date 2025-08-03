package com.internetbanking.api.model.dto;

import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,
        @NotBlank(message = "O nome não pode estar em branco")
        String name,
        @NotBlank(message = "O CPF não pode estar em branco")
        String cpf,
        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Formato de email inválido")
        String email,
        @NotBlank(message = "A senha não pode estar em branco")
        String password,
        BankingAccount account
) {
    public UserDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getPassword(),
                user.getAccount()
        );
    }
}