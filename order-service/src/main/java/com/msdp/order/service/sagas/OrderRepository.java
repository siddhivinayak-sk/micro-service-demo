package com.msdp.order.service.sagas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
	List<Orders> findAllByOrderDetailsCustomerId(Long customerId);
}
