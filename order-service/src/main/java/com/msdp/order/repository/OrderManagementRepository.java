package com.msdp.order.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msdp.order.model.Order;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface OrderManagementRepository extends CrudRepository<Order, Integer> {

}
