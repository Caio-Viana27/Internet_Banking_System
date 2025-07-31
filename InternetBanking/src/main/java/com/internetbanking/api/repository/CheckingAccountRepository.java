package com.internetbanking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internetbanking.api.model.entity.CheckingAccount;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {

}
