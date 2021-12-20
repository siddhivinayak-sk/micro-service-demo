package com.msdp.delivery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.delivery.model.DeliveryDetails;
import com.msdp.delivery.service.DeliveryMangementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@RequestMapping(value = "/delivery", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/delivery", description = "Opeartions pertaining to Delivery Management")
public class DeliveryMangementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryMangementController.class);

	@Autowired
	private DeliveryMangementService deliveryMangementService;

	@PostMapping
	@ApiOperation(value = "Creates the delivery details")
	public DeliveryDetails createDeliveryDetails(@RequestBody DeliveryDetails delivery) {
		LOGGER.info("Executing DeliveryMangementController: createDeliveryDetails method");
		DeliveryDetails deliveryDetails = deliveryMangementService.createDeliveryDetails(delivery);
		return deliveryDetails;
	}

	@GetMapping("/{customerId}")
	@ApiOperation(value = "Get the delivery details against given Customer Id")
	public List<DeliveryDetails> getDeliveryDetailByCustomerId(@PathVariable("customerId") int customerId) {
		LOGGER.info("Executing DeliveryMangementController: createDeliveryDetails method");
		return deliveryMangementService.findDeliveryDetailByCustomerId(customerId);
	}

	@PutMapping
	@ApiOperation(value = "Updates the delivery information")
	public DeliveryDetails updateDelivery(@RequestBody DeliveryDetails deliveryDetails) {
		LOGGER.info("Executing DeliveryMangementController: createDeliveryDetails method");
		return deliveryMangementService.updateDeliveryDetails(deliveryDetails);
	}

	@DeleteMapping("/{deliveryId}")
	@ApiOperation(value = "Deletes the entry of given delivery Id")
	public void deleteDelivery(@PathVariable("deliveryId") int deliveryId) {
		LOGGER.info("Executing DeliveryMangementController: createDeliveryDetails method");
		deliveryMangementService.deleteDeliveryDetails(deliveryId);
	}

	@GetMapping("/poll")
	@ApiOperation(value = "This endpoint is exposed to check if the services is up and running")
	public String testGetMethod() {
		LOGGER.info("Executing DeliveryMangementController: createDeliveryDetails method");
		return "YES";
	}

}
