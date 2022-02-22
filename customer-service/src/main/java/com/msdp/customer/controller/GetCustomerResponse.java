package com.msdp.customer.controller;

import com.msdp.order.service.sagas.Money;

public class GetCustomerResponse {
	private Long customerId;
	private String name;
	private Money creditLimit;
	private Money availableCredit;

	public GetCustomerResponse() {
	}

	public GetCustomerResponse(Long customerId, String name, Money creditLimit, Money availableCredit) {
		this.customerId = customerId;
		this.name = name;
		this.creditLimit = creditLimit;
		this.availableCredit = availableCredit;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public Money getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(Money availableCredit) {
		this.availableCredit = availableCredit;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Money creditLimit) {
		this.creditLimit = creditLimit;
	}
}
