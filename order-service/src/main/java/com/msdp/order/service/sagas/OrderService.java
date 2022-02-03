package com.msdp.order.service.sagas;

public class OrderService {

	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Orders createOrder(OrderDetails orderDetails) {
		Orders order = Orders.createOrder(orderDetails);
		orderRepository.save(order);
		return order;
	}

	public void approveOrder(Long orderId) {
		orderRepository.findById(orderId).get().approve();
	}

	public void rejectOrder(Long orderId, RejectionReason rejectionReason) {
		orderRepository.findById(orderId).get().reject(rejectionReason);
	}
}
