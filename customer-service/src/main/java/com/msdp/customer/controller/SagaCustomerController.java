package com.msdp.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.customer.service.sagas.CustomerRepositorySaga;
import com.msdp.customer.service.sagas.CustomerServiceSaga;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.msdp.customer.service.sagas.Customer;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@Api(value = "/customer", description = "Opeartions pertaining to Customer Management")
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class SagaCustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SagaCustomerController.class);
	private CustomerServiceSaga customerService;

	private CustomerRepositorySaga customerRepository;

	@Autowired
	public SagaCustomerController(CustomerServiceSaga customerService, CustomerRepositorySaga customerRepository) {
		this.customerService = customerService;
		this.customerRepository = customerRepository;
	}

	@ApiOperation(value = "Stores the Customer Information")
	@PostMapping
	public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		LOGGER.info("Executing SagaCustomerController: createCustomer method");
		Customer customer = customerService.createCustomer(createCustomerRequest.getName(),
				createCustomerRequest.getCreditLimit());
		return new CreateCustomerResponse(customer.getId());
	}

//	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
//	public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long customerId) {
//		return customerRepository.findById(customerId)
//				.map(c -> new ResponseEntity<>(
//						new GetCustomerResponse(c.getId(), c.getName(), c.getCreditLimit(), c.availableCredit()),
//						HttpStatus.OK))
//				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}

	@ApiOperation(value = "Retrive all customers")
	@GetMapping
	public List<Customer> getAllCustomer() {
		LOGGER.info("Executing CustomerController: getAllCustomer method");
		return customerService.getAllCustomer();
	}

	@ApiOperation(value = "returns the customer details based on customer ID")
	@GetMapping("/{customerId}")
	public Customer getCustomerDetails(@PathVariable("customerId") int customerId) {
		LOGGER.info("Executing CustomerController: getCustomerDetails method");
		return customerService.getCustomer(customerId);
	}

	@ApiOperation(value = "Delete the customer base on its CustomerID")
	@DeleteMapping("/{custometId}")
	public void deleteCustomer(@PathVariable("custometId") int customerId) {
		LOGGER.info("Executing CustomerController: deleteCustomer method");
		customerService.deleteCustomer(customerId);
	}

	@ApiOperation(value = "updates the Customer details")
	@PutMapping
	public Customer updateCustomer(@RequestBody Customer customer) {
		LOGGER.info("Executing CustomerController: updateCustomer method");
		Customer updatedCustomer = customerService.updateCustomer(customer);
		LOGGER.info("Exiting CustomerController: updateCustomer method");
		return updatedCustomer;
	}

	@ApiOperation(value = "Use this endpoint to check if the services is up and running")
	@GetMapping("/poll")
	public String testGetMethod() {
		LOGGER.info("Executing CustomerController: testGetMethod method");
		return "YES";
	}
}
