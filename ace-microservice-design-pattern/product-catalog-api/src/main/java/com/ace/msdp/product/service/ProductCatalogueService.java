package com.ace.msdp.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ace.msdp.product.model.Product;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public interface ProductCatalogueService {
	
	public Product addProductDetails(Product product);
	public Product getProduct(int productId);
	public void deleteProduct(int productId);
	public Product updateProduct(Product product);
	List<Product> getAllProduct();
}
