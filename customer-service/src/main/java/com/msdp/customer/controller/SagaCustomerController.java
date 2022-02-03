package com.msdp.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.customer.service.sagas.CustomerRepositorySaga;
import com.msdp.customer.service.sagas.CustomerServiceSaga;
import com.msdp.customer.service.sagas.Customers;

@RestController
public class SagaCustomerController {

	private CustomerServiceSaga customerService;

	private CustomerRepositorySaga customerRepository;

	@Autowired
	public SagaCustomerController(CustomerServiceSaga customerService, CustomerRepositorySaga customerRepository) {
		this.customerService = customerService;
		this.customerRepository = customerRepository;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		Customers customer = customerService.createCustomer(createCustomerRequest.getName(),
				createCustomerRequest.getCreditLimit());
		return new CreateCustomerResponse(customer.getId());
	}

	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long customerId) {
		return customerRepository.findById(customerId)
				.map(c -> new ResponseEntity<>(new GetCustomerResponse(c.getId(), c.getName(), c.getCreditLimit()),
						HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
