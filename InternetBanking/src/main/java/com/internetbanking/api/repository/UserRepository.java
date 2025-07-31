package com.internetbanking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internetbanking.api.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
