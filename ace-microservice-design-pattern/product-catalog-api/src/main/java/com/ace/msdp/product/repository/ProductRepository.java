package com.ace.msdp.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ace.msdp.product.model.Product;

/**
 * @author karuneshkumar.s
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
