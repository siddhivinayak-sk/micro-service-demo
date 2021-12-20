package com.msdp.inventory.controller;

import java.util.List;

import javax.ws.rs.Produces;

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

import com.msdp.inventory.model.Inventory;
import com.msdp.inventory.service.InventoryManagementService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@RequestMapping(value = "/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/inventory", description = "Operations pertaining to Inventory Management")
public class InventoryManagementController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryManagementController.class);

	@Autowired
	private InventoryManagementService inventoryManagementService;

	@PostMapping
	@ApiOperation(value = "Add the new inventory into the system")
	public Inventory addInventoryDetails(@RequestBody Inventory inventory) {
		LOGGER.info("Executing InventoryManagementController: addInventoryDetails method");
		return inventoryManagementService.addInventoryDetails(inventory);
	}

	@PutMapping
	@ApiOperation(value = "Updates the new inventory details into the system")
	public Inventory updateInventory(@RequestBody Inventory inventory) {
		LOGGER.info("Executing InventoryManagementController: updateInventory method");
		return inventoryManagementService.updateInventory(inventory);
	}

	@DeleteMapping("/{inventoryId}")
	@ApiOperation(value = "Deletes the given inventory details from the system")
	public void deleteInventory(@PathVariable("inventoryId") int inventoryId) {
		LOGGER.info("Executing InventoryManagementController: deleteInventory method");
		inventoryManagementService.deleteInventory(inventoryId);
	}

	@GetMapping("/{productId}")
	@ApiOperation(value = "Retrieves the Inventory details against the given product ID.")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Inventory getInventory(@PathVariable("productId") int productId) {
		LOGGER.info("Executing InventoryManagementController: getInventory method");
		return inventoryManagementService.findInventoryByProductId(productId);
	}

	@GetMapping
	@ApiOperation(value = "Retrieves all Inventory.")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<Inventory> getAllInventory() {
		LOGGER.info("Executing InventoryManagementController: getAllInventory method");
		return inventoryManagementService.findAllInventory();
	}

	@GetMapping("/poll")
	@ApiOperation(value = " This endpoint is to check if the service is up and running")
	public String testGetMethod() {
		LOGGER.info("Executing InventoryManagementController: Polling the services");
		return "YES";
	}

}
