package com.msdp.customer.service.sagas;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositorySaga extends CrudRepository<Customer, Long> {
}
