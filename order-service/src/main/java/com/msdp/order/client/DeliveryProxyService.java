package com.msdp.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.msdp.order.model.DeliveryDetails;

/**
 * @author karuneshkumar.s
 *
 */
@FeignClient("delivery-service")
public interface DeliveryProxyService {

	@PostMapping("/delivery")
	public DeliveryDetails createDeliveryDetails(@RequestBody DeliveryDetails delivery);

}
