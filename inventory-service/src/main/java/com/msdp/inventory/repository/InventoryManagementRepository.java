package com.msdp.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.msdp.inventory.model.Inventory;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface InventoryManagementRepository extends CrudRepository<Inventory, Integer> {

	public Inventory findInventoryByProductId(int productId);

}
