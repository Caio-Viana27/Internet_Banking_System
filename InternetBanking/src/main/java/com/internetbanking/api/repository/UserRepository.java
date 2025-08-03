package com.internetbanking.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.internetbanking.api.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByCpf(String cpf);
    Optional<UserDetails> findByEmail(String email);
}
