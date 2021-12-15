package com.ace.msdp.product.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.msdp.product.model.Product;
import com.ace.msdp.product.repository.ProductRepository;
import com.ace.msdp.product.service.ProductCatalogueService;

/**
 * @author karuneshkumar.s
 *
 */
@Service
public class ProductCatalogueServiceImpl implements ProductCatalogueService {

	@Autowired
	private ProductRepository productCatalogueRespository;

	@Override
	public Product addProductDetails(Product product) {
		return productCatalogueRespository.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		return (List<Product>) productCatalogueRespository.findAll();
	}

	@Override
	public Product getProduct(int productId) {
		Optional<Product> product = productCatalogueRespository.findById(productId);
		Product productDetails = new Product();
		if (product.isPresent()) {
			productDetails = product.get();
		}

		return productDetails;
	}

	@Override
	public void deleteProduct(int productId) {
		productCatalogueRespository.deleteById(productId);
	}

	@Override
	public Product updateProduct(Product product) {
		return productCatalogueRespository.save(product);
	}
}
