package com.ace.msdp.order.controller;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.msdp.order.client.DeliveryProxyService;
import com.ace.msdp.order.client.InventoryProxyService;
import com.ace.msdp.order.client.ProductProxyService;
import com.ace.msdp.order.model.DeliveryDetails;
import com.ace.msdp.order.model.Inventory;
import com.ace.msdp.order.model.Order;
import com.ace.msdp.order.model.Product;
import com.ace.msdp.order.service.OrderManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/order", description = "Opeartions pertaining to Order Management")
public class OrderManagementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderManagementController.class);

	@Autowired
	private OrderManagementService orderManagementService;

	@Autowired
	private ProductProxyService productProxyService;

	@Autowired
	private InventoryProxyService inventoryProxyService;

	@Autowired
	private DeliveryProxyService deliveryProxyService;

	@PostMapping
	@ApiOperation(value = "Creates the order against the supplied data")
	public void createOrders(@RequestBody Order order) {
		LOGGER.info("Executing OrderManagementController: createOrders method");

		Product product = productProxyService.findProduct(order.getProductId());
		// fetch product details using productId coming from product.
		Inventory inventory = inventoryProxyService.findInventoryByProductId(order.getProductId());

		// update available inventory.
		int count = inventory.getCount() - order.getQty();
		inventory.setCount(count);
		inventoryProxyService.updateInventory(inventory);
		LOGGER.info("Executing OrderManagementController : Inventory updated");

		DeliveryDetails deliveryDetails = new DeliveryDetails();
		deliveryDetails.setCustomerId(order.getCustomerId());
		deliveryDetails.setProductId(order.getProductId());

		// get the current date and set delivery time after 3 days to the order date.
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 3);

		deliveryDetails.setOrderDate(date);
		deliveryDetails.setDeliveryDate(calendar.getTime());
		deliveryProxyService.createDeliveryDetails(deliveryDetails);

		order.setOrderValue(product.getPrice() * order.getQty());
		orderManagementService.createOrder(order);
		LOGGER.info("Executing OrderManagementController : Order created successfully");

	}

	@GetMapping("/poll")
	@ApiOperation(value = "This endpoint is exposed to check if the services is up and running")
	public String testGetMethod() {
		LOGGER.info("Executing OrderManagementController: Polling the service");
		return "YES";
	}

}
