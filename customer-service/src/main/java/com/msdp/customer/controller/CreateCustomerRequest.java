package com.msdp.customer.controller;

import com.msdp.order.service.sagas.Money;

public class CreateCustomerRequest {
	private String name;
	private Money creditLimit;

	public CreateCustomerRequest() {
	}

	public CreateCustomerRequest(String name, Money creditLimit) {

		this.name = name;
		this.creditLimit = creditLimit;
	}

	public String getName() {
		return name;
	}

	public Money getCreditLimit() {
		return creditLimit;
	}
}
