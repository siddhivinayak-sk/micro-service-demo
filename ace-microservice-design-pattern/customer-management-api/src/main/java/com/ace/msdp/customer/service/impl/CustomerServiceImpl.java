package com.ace.msdp.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.msdp.customer.model.Customer;
import com.ace.msdp.customer.repository.CustomerRepository;
import com.ace.msdp.customer.service.CustomerService;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRespository;

	@Override
	public Customer addCustomerDetails(Customer customer) {
		LOGGER.info("Executing CustomerServiceImpl : addCustomerDetails. Customer Data" + customer.toString());
		Customer savedCustomer = customerRespository.save(customer);
		return savedCustomer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers = (List<Customer>) customerRespository.findAll();
		LOGGER.info("Executing CustomerServiceImpl : getAllCustomer");
		return customers;
	}

	@Override
	public Customer getCustomer(int customerId) {
		Customer customerDetails = null;
		Optional<Customer> customer = customerRespository.findById(customerId);
		LOGGER.info("Executing CustomerServiceImpl : getCustomer. Customer Data" + customer.toString());
		if (customer.isPresent()) {
			customerDetails = customer.get();
		}
		return customerDetails;
	}

	@Override
	public void deleteCustomer(int customerId) {
		LOGGER.info("Executing CustomerServiceImpl : getCustomer. Deleting customer with id" + customerId);
		customerRespository.deleteById(customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		LOGGER.info("Executing CustomerServiceImpl : updateCustomer. updating customer" + customer.toString());
		Customer updatedCustomer = customerRespository.save(customer);
		return updatedCustomer;
	}

}
