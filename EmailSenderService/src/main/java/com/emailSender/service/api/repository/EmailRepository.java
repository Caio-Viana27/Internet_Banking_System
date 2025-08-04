package com.emailSender.service.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emailSender.service.api.model.entity.EmailEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}
