package com.msdp.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msdp.customer.model.Customer;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public interface CustomerService {

	public Customer addCustomerDetails(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public Customer updateCustomer(Customer customer);

	List<Customer> getAllCustomer();
}
