package com.msdp.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.customer.model.Customer;
import com.msdp.customer.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@Api(value = "/customer", description = "Opeartions pertaining to Customer Management")
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Stores the Customer Information")
	@PostMapping
	public Customer addCustomerDetails(@RequestBody Customer customer) {
		LOGGER.info("Executing CustomerController: addCustomerDetails method");
		Customer savedCustomer = customerService.addCustomerDetails(customer);
		return savedCustomer;
	}

	@ApiOperation(value = "Retrive all customers")
	@GetMapping
	public List<Customer> getAllCustomer() {
		LOGGER.info("Executing CustomerController: getAllCustomer method");
		return customerService.getAllCustomer();
	}

	@ApiOperation(value = "returns the customer details based on customer ID")
	@GetMapping("/{customerId}")
	public Customer getCustomerDetails(@PathVariable("customerId") String customerId) {
		LOGGER.info("Executing CustomerController: getCustomerDetails method");
		return customerService.getCustomer(Integer.parseInt(customerId));
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
