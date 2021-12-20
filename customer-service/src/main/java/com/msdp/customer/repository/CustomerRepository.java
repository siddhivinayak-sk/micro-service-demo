package com.msdp.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msdp.customer.model.Customer;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
