package com.msdp.order.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.msdp.order.service.sagas.CreateOrderSaga;
import com.msdp.order.service.sagas.OrderRepository;
import com.msdp.order.service.sagas.OrderSagaService;
import com.msdp.order.service.sagas.OrderService;

import io.eventuate.tram.jdbcactivemq.TramJdbcActiveMQConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import io.eventuate.tram.sagas.spring.orchestration.SagaOrchestratorConfiguration;

@Configuration
@EnableAutoConfiguration
@Import({ SagaOrchestratorConfiguration.class, TramJdbcActiveMQConfiguration.class })
public class OrderConfiguration {

	@Bean
	public OrderSagaService orderSagaService(OrderRepository orderRepository, SagaInstanceFactory sagaInstanceFactory,
			CreateOrderSaga createOrderSaga) {
		return new OrderSagaService(orderRepository, sagaInstanceFactory, createOrderSaga);
	}

	@Bean
	public OrderService orderService(OrderRepository orderRepository) {
		return new OrderService(orderRepository);
	}

	@Bean
	public CreateOrderSaga createOrderSaga(OrderService orderService) {
		return new CreateOrderSaga(orderService);
	}
}
