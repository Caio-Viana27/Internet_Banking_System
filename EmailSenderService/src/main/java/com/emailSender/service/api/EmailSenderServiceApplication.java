package com.emailSender.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmailSenderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailSenderServiceApplication.class, args);
	}

}
