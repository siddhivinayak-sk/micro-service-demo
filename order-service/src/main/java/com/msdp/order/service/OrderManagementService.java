package com.msdp.order.service;

import org.springframework.stereotype.Service;

import com.msdp.order.model.Order;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public interface OrderManagementService {
	public void createOrder(Order order);

	public Order updateOrder(Order order);
}
