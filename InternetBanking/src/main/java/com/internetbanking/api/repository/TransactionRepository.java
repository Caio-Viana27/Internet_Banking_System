package com.internetbanking.api.repository;

import com.internetbanking.api.model.entity.BankingAccount;
import com.internetbanking.api.model.entity.Transaction;
import com.internetbanking.api.model.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountOrderByDateTimeDesc(BankingAccount account);
}