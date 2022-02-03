package com.msdp.order.service.sagas;

import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;

public class OrderSagaService {

	private OrderRepository orderRepository;

	private SagaInstanceFactory sagaInstanceFactory;

	private CreateOrderSaga createOrderSaga;

	public OrderSagaService(OrderRepository orderRepository, SagaInstanceFactory sagaInstanceFactory, CreateOrderSaga createOrderSaga) {
	    this.orderRepository = orderRepository;
	    this.sagaInstanceFactory = sagaInstanceFactory;
	    this.createOrderSaga = createOrderSaga;
	  }

	@Transactional
	public Orders createOrder(OrderDetails orderDetails) {
		CreateOrderSagaData data = new CreateOrderSagaData(orderDetails);
		sagaInstanceFactory.create(createOrderSaga, data);
		return orderRepository.findById(data.getOrderId()).get();
	}
}