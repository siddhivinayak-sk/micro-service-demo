package com.ace.msdp.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.msdp.order.model.Order;
import com.ace.msdp.order.repository.OrderManagementRepository;
import com.ace.msdp.order.service.OrderManagementService;

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

}
