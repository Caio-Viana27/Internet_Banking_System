package com.internetbanking.api.model.entity;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.internetbanking.api.model.CPF;
import com.internetbanking.api.model.Email;
import com.internetbanking.api.model.Name;
import com.internetbanking.api.model.Password;
import com.internetbanking.api.model.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private Name name;
	
	@NotNull
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private CPF cpf;
	
	@NotNull
	@JdbcTypeCode(SqlTypes.VARCHAR)
 	private Email email;
	
	@NotNull
	@JdbcTypeCode(SqlTypes.VARCHAR)
	private Password password;
	
	@NotNull
	@OneToMany(mappedBy = "user")
	private List<CheckingAccount> accounts;

	public User(Long id,
			@NotNull Name name,
			@NotNull CPF cpf,
			@NotNull Email email,
			@NotNull Password password,
			@NotNull List<CheckingAccount> accounts) {
		
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.accounts = accounts;
	}
	
	public User(UserDTO userDTO) {
		super();
		this.id = userDTO.id();
		this.name = userDTO.name();
		this.cpf = userDTO.cpf();
		this.email = userDTO.email();
		this.password = userDTO.password();
		this.accounts = userDTO.accounts();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	public List<CheckingAccount> getAccounts() {
		return accounts;
	}

	public void setAccount(List<CheckingAccount> accounts) {
		this.accounts = accounts;
	}
	
	
}
