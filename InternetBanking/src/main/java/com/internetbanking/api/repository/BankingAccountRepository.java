package com.internetbanking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.internetbanking.api.model.entity.BankingAccount;

@Repository
public interface BankingAccountRepository extends JpaRepository<BankingAccount, Long> {

}
