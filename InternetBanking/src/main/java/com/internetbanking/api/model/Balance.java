package com.internetbanking.api.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class Balance {
	
	@NotNull
	private BigDecimal amount;
	
	public Balance(@NotNull BigDecimal amount) {
		super();
		this.amount = amount;
	}

	public void add(BigDecimal value) throws Exception {
		if (value.compareTo(BigDecimal.ZERO) <= 0)
			throw new Exception();

		amount = amount.add(value);
	}
	
	public void remove(BigDecimal value) throws Exception {
		if (value.compareTo(BigDecimal.ZERO) <= 0)
			throw new Exception();
		
		amount = amount.subtract(value);
	}
}
