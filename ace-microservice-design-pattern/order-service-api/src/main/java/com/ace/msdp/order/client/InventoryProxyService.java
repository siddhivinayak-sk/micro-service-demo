package com.ace.msdp.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ace.msdp.order.model.Inventory;

/**
 * @author karuneshkumar.s
 *
 */
@FeignClient("inventory-management")
public interface InventoryProxyService {

	@GetMapping("/inventory/{productId}")
	public Inventory findInventoryByProductId(@PathVariable(value = "productId") int productId);

	@PutMapping("/inventory")
	public Inventory updateInventory(@RequestBody Inventory inventory);

}
