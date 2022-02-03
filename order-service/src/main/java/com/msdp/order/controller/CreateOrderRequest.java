package com.msdp.order.controller;

import com.msdp.order.service.sagas.Money;

public class CreateOrderRequest {
	private Money orderTotal;
	private Long customerId;

	public CreateOrderRequest() {
	}

	public CreateOrderRequest(Long customerId, Money orderTotal) {
		this.customerId = customerId;
		this.orderTotal = orderTotal;
	}

	public Money getOrderTotal() {
		return orderTotal;
	}

	public Long getCustomerId() {
		return customerId;
	}
}
