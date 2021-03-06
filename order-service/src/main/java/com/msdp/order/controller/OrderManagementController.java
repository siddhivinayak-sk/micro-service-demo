package com.msdp.order.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msdp.order.client.CustomerProxyService;
import com.msdp.order.client.DeliveryProxyService;
import com.msdp.order.client.InventoryProxyService;
import com.msdp.order.client.ProductProxyService;
import com.msdp.order.model.Customer;
import com.msdp.order.model.DeliveryDetails;
import com.msdp.order.model.Inventory;
import com.msdp.order.model.Order;
import com.msdp.order.model.OrderDetail;
import com.msdp.order.model.Product;
import com.msdp.order.service.OrderManagementService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
	private static final String ORDER_SERVICE = "orderService";
	
	@Autowired
	private OrderManagementService orderManagementService;

	@Autowired
	private ProductProxyService productProxyService;

	@Autowired
	private InventoryProxyService inventoryProxyService;

	@Autowired
	private DeliveryProxyService deliveryProxyService;

	@Autowired
	private CustomerProxyService customerProxyService;

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

		order.setOrderValue(product.getPrice() * order.getQty());
		orderManagementService.createOrder(order);

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

		LOGGER.info("Executing OrderManagementController : Order created successfully");

	}

	@GetMapping("/detail/{customerId}")
	@ApiOperation(value = "Get the Orders summary against given Customer Id")
	@CircuitBreaker(name=ORDER_SERVICE, fallbackMethod = "orderFallback")
	public ResponseEntity<Object> getOrderDetailByCustomerId(@PathVariable("customerId") int customerId) {
		LOGGER.info("Executing OrderMangementController: getOrderDetailsByCustomerId method");
		List<OrderDetail> orderDetailResponse = new ArrayList<>();

		Customer customer = customerProxyService.getCustomerDetails(customerId);

		List<Product> products = productProxyService.findAllProduct();
		Map<Integer, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		List<Order> orders = orderManagementService.findOrderDetailByCustomerId(customerId);

		if (orders != null && !orders.isEmpty()) {
			orderDetailResponse = orders.stream().map(order -> {
				Product product = productMap.get(order.getProductId());
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setCustomerName(customer.getName());
				orderDetail.setProductName(product.getName());
				orderDetail.setBrand(product.getBrand());
				orderDetail.setQty(order.getQty());
				orderDetail.setOrderValue(order.getOrderValue());
				return orderDetail;
			}).collect(Collectors.toList());
		}
		LOGGER.info("Order detials retrived.");
		return new ResponseEntity<>(orderDetailResponse, HttpStatus.OK);
	}

	public ResponseEntity<Object> orderFallback(int customerId,Exception e){
		LOGGER.info("Service is down: Please try again later");
		return new ResponseEntity<>("Service is down: Please try again later.", HttpStatus.OK);
    }
	
	@GetMapping("/poll")
	@ApiOperation(value = "This endpoint is exposed to check if the services is up and running")
	public String testGetMethod() {
		LOGGER.info("Executing OrderManagementController: Polling the service");
		return "YES";
	}

}
