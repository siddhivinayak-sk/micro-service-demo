package com.msdp.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.msdp.inventory.model.Inventory;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public interface InventoryManagementService {
	public Inventory addInventoryDetails(Inventory inventory);

	public void deleteInventory(int inventoryId);

	public Inventory updateInventory(Inventory inventory);

	public Inventory findInventoryByProductId(int productId);

	List<Inventory> findAllInventory();
}
