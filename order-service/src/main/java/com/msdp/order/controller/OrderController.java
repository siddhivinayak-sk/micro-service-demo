package com.msdp.order.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.order.service.sagas.OrderDetails;
import com.msdp.order.service.sagas.OrderRepository;
import com.msdp.order.service.sagas.OrderSagaService;
import com.msdp.order.service.sagas.Orders;

@RestController
public class OrderController {

	@Autowired
	private OrderSagaService orderSagaService;
	
	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
		Orders order = orderSagaService
				.createOrder(new OrderDetails(createOrderRequest.getCustomerId(), createOrderRequest.getOrderTotal()));
		return new CreateOrderResponse(order.getId());
	}

	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<GetOrderResponse> getOrder(@PathVariable Long orderId) {
		return orderRepository.findById(orderId)
				.map(o -> new ResponseEntity<>(new GetOrderResponse(o.getId(), o.getState(), o.getRejectionReason()),
						HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(value = "/orders/customer/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<List<GetOrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId) {
		return new ResponseEntity<List<GetOrderResponse>>(orderRepository.findAllByOrderDetailsCustomerId(customerId)
				.stream().map(o -> new GetOrderResponse(o.getId(), o.getState(), o.getRejectionReason()))
				.collect(Collectors.toList()), HttpStatus.OK);
	}
}