package com.msdp.customer.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.msdp.customer.service.sagas.CustomerCommandHandler;
import com.msdp.customer.service.sagas.CustomerRepositorySaga;
import com.msdp.customer.service.sagas.CustomerServiceSaga;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.jdbcactivemq.TramJdbcActiveMQConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import io.eventuate.tram.sagas.spring.participant.SagaParticipantConfiguration;

@Configuration
@Import({ SagaParticipantConfiguration.class, TramJdbcActiveMQConfiguration.class })
@EnableAutoConfiguration
public class CustomerConfiguration {

  @Bean
  public CustomerServiceSaga customerService(CustomerRepositorySaga customerRepository) {
    return new CustomerServiceSaga(customerRepository);
  }

  @Bean
  public CustomerCommandHandler customerCommandHandler(CustomerServiceSaga customerService) {
    return new CustomerCommandHandler(customerService);
  }

  // TODO Exception handler for CustomerCreditLimitExceededException

  @Bean
  public CommandDispatcher consumerCommandDispatcher(CustomerCommandHandler target,
                                                     SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {

    return sagaCommandDispatcherFactory.make("customerCommandDispatcher", target.commandHandlerDefinitions());
  }

}