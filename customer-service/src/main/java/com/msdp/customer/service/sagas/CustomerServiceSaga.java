package com.msdp.customer.service.sagas;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.msdp.order.service.sagas.Money;

public class CustomerServiceSaga {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceSaga.class);
	private CustomerRepositorySaga customerRepository;

	public CustomerServiceSaga(CustomerRepositorySaga customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Transactional
	public Customer createCustomer(String name, Money creditLimit) {
		LOGGER.info("Executing CustomerServiceSaga : createCustomer. Customer Data" + name);
		Customer customer = new Customer(name, creditLimit);
		return customerRepository.save(customer);
	}

	public void reserveCredit(long customerId, long orderId, Money orderTotal)
			throws CustomerCreditLimitExceededException {
		LOGGER.info("Executing CustomerServiceSaga : reserveCredit. customerId " + customerId);
		Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
		customer.reserveCredit(orderId, orderTotal);
	}

	public List<Customer> getAllCustomer() {
		LOGGER.info("Executing CustomerServiceImpl : getAllCustomer");
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
		return customers;
	}

	public Customer getCustomer(long customerId) {
		Customer customerDetails = null;
		Optional<Customer> customer = customerRepository.findById(customerId);
		LOGGER.info("Executing CustomerServiceImpl : getCustomer. Customer Data" + customer.toString());
		if (customer.isPresent()) {
			customerDetails = customer.get();
		}
		return customerDetails;
	}

	public void deleteCustomer(long customerId) {
		LOGGER.info("Executing CustomerServiceImpl : getCustomer. Deleting customer with id" + customerId);
		customerRepository.deleteById(customerId);
	}

	public Customer updateCustomer(Customer customer) {
		LOGGER.info("Executing CustomerServiceImpl : updateCustomer. updating customer" + customer.toString());
		Customer updatedCustomer = customerRepository.save(customer);
		return updatedCustomer;
	}

}
