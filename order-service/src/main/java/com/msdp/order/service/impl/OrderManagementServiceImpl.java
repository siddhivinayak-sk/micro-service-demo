package com.msdp.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msdp.order.model.Order;
import com.msdp.order.repository.OrderManagementRepository;
import com.msdp.order.service.OrderManagementService;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public class OrderManagementServiceImpl implements OrderManagementService {

	@Autowired
	private OrderManagementRepository orderManagementRepository;

	@Override
	public void createOrder(Order order) {
		orderManagementRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findOrderDetailByCustomerId(int customerId) {
		return orderManagementRepository.findByCustomerId(customerId);
	}

}
