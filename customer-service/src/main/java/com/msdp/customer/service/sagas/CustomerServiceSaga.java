package com.msdp.customer.service.sagas;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msdp.order.service.sagas.Money;

public class CustomerServiceSaga {

	private CustomerRepositorySaga customerRepository;

	public CustomerServiceSaga(CustomerRepositorySaga customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Customers createCustomer(String name, Money creditLimit) {
		Customers customer = new Customers(name, creditLimit);
		return customerRepository.save(customer);
	}

	public void reserveCredit(long customerId, long orderId, Money orderTotal)
			throws CustomerCreditLimitExceededException {
		Customers customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customer.reserveCredit(orderId, orderTotal);
	}
}
