package com.ace.msdp.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ace.msdp.customer.model.Customer;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
