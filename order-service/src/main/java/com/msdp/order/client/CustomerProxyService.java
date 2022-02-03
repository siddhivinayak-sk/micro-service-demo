package com.msdp.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msdp.order.model.Customer;

/**
 * @author karuneshkumar.s
 *
 */
@FeignClient("customer-service")
public interface CustomerProxyService {

	@GetMapping("/customer/{customerId}")
	public Customer getCustomerDetails(@PathVariable("customerId") int customerId);

}
