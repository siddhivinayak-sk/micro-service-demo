package com.msdp.delivery.repository.write;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msdp.delivery.model.DeliveryDetails;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface DeliveryMangementRepository extends CrudRepository<DeliveryDetails, Integer> {

	public List<DeliveryDetails> findByCustomerId(int customerId);
}
