package com.msdp.order.service.sagas;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData> {

	private OrderService orderService;

	public CreateOrderSaga(OrderService orderService) {
		this.orderService = orderService;
	}

	private SagaDefinition<CreateOrderSagaData> sagaDefinition = 
			step()
				.invokeLocal(this::create)
				.withCompensation(this::reject)
			.step()
				.invokeParticipant(this::reserveCredit)
				.onReply(CustomerNotFound.class, this::handleCustomerNotFound)
				.onReply(CustomerCreditLimitExceeded.class, this::handleCustomerCreditLimitExceeded)
				//.onReply(CustomerCreditReserved.class, this::handleOrderCreated)
			.step()
				.invokeLocal(this::approve)
			.build();

	private void handleCustomerNotFound(CreateOrderSagaData data, CustomerNotFound reply) {
		data.setRejectionReason(RejectionReason.UNKNOWN_CUSTOMER);
	}

	private void handleCustomerCreditLimitExceeded(CreateOrderSagaData data, CustomerCreditLimitExceeded reply) {
		data.setRejectionReason(RejectionReason.INSUFFICIENT_CREDIT);
	}
	
//	private void handleOrderCreated(CreateOrderSagaData data, CustomerCreditReserved reply) {
//		//data.setRejectionReason(RejectionReason.EMPTY);
//	}

	@Override
	public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
		return this.sagaDefinition;
	}

	private void create(CreateOrderSagaData data) {
		Orders order = orderService.createOrder(data.getOrderDetails());
		data.setOrderId(order.getId());
	}

	private CommandWithDestination reserveCredit(CreateOrderSagaData data) {
		long orderId = data.getOrderId();
		Long customerId = data.getOrderDetails().getCustomerId();
		Money orderTotal = data.getOrderDetails().getOrderTotal();
		return send(new ReserveCreditCommand(customerId, orderId, orderTotal)).to("customerService").build();
	}

	private void approve(CreateOrderSagaData data) {
		orderService.approveOrder(data.getOrderId());
	}

	private void reject(CreateOrderSagaData data) {
		orderService.rejectOrder(data.getOrderId(), data.getRejectionReason());
	}
}