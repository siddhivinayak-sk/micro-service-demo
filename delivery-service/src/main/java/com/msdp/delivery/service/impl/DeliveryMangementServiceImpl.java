package com.msdp.delivery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msdp.delivery.model.DeliveryDetails;
import com.msdp.delivery.repository.write.DeliveryMangementRepository;
import com.msdp.delivery.service.DeliveryMangementService;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public class DeliveryMangementServiceImpl implements DeliveryMangementService {

	@Autowired
	private DeliveryMangementRepository deliveryMangementRepository;

	@Override
	public DeliveryDetails createDeliveryDetails(DeliveryDetails deliveryDetails) {
		DeliveryDetails savedDeliveryDetails = deliveryMangementRepository.save(deliveryDetails);
		return savedDeliveryDetails;
	}

	@Override
	public DeliveryDetails updateDeliveryDetails(DeliveryDetails deliveryDetails) {
		DeliveryDetails updatedDeliveryDetails = deliveryMangementRepository.save(deliveryDetails);
		return updatedDeliveryDetails;
	}

	@Override
	public void deleteDeliveryDetails(int deliveryId) {
		deliveryMangementRepository.deleteById(deliveryId);
	}

	@Override
	public List<DeliveryDetails> findDeliveryDetailByCustomerId(int customerid) {
		List<DeliveryDetails> deliveries = deliveryMangementRepository.findByCustomerId(customerid);
		return deliveries;
	}

}
