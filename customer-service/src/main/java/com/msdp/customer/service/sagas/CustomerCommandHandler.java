package com.msdp.customer.service.sagas;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import com.msdp.order.service.sagas.CustomerCreditReserved;
import com.msdp.order.service.sagas.ReserveCreditCommand;

public class CustomerCommandHandler {

	private CustomerServiceSaga customerService;

	public CustomerCommandHandler(CustomerServiceSaga customerService) {
		this.customerService = customerService;
	}

	public CommandHandlers commandHandlerDefinitions() {
		return SagaCommandHandlersBuilder.fromChannel("customerService")
				.onMessage(ReserveCreditCommand.class, this::reserveCredit).build();
	}

	public Message reserveCredit(CommandMessage<ReserveCreditCommand> cm) {
		ReserveCreditCommand cmd = cm.getCommand();
		try {
			System.out.println(cmd.getOrderId()+" Message is consumed");
			customerService.reserveCredit(cmd.getCustomerId(), cmd.getOrderId(), cmd.getOrderTotal());
			return withSuccess(new CustomerCreditReserved());
		} catch (CustomerNotFoundException e) {
			return withFailure(new CustomerNotFound());
		} catch (CustomerCreditLimitExceededException e) {
			return withFailure(new CustomerCreditLimitExceeded());
		}
	}

}