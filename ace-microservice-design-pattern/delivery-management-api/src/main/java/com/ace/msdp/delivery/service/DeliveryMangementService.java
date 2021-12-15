package com.ace.msdp.delivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ace.msdp.delivery.model.DeliveryDetails;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public interface DeliveryMangementService {
	
	public DeliveryDetails createDeliveryDetails(DeliveryDetails deliveryDetails);
	public DeliveryDetails updateDeliveryDetails(DeliveryDetails deliveryDetails);
	public void deleteDeliveryDetails(int deliveryId);
	public List<DeliveryDetails> findDeliveryDetailByCustomerId(int customerid);
}
