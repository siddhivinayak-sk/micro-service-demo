package com.ace.msdp.inventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.msdp.inventory.model.Inventory;
import com.ace.msdp.inventory.repository.InventoryManagementRepository;
import com.ace.msdp.inventory.service.InventoryManagementService;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public class InventoryManagementServiceImpl implements InventoryManagementService {

	@Autowired
	private InventoryManagementRepository inventoryManagementRespository;

	@Override
	public Inventory addInventoryDetails(Inventory inventory) {
		return inventoryManagementRespository.save(inventory);
	}

	@Override
	public Inventory findInventoryByProductId(int productId) {
		return inventoryManagementRespository.findInventoryByProductId(productId);
	}

	@Override
	public List<Inventory> findAllInventory() {
		return (List<Inventory>) inventoryManagementRespository.findAll();
	}

	@Override
	public void deleteInventory(int inventoryId) {
		inventoryManagementRespository.deleteById(inventoryId);
	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		return inventoryManagementRespository.save(inventory);
	}

}
