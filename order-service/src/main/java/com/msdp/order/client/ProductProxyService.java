package com.msdp.order.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msdp.order.model.Product;

/**
 * @author karuneshkumar.s
 *
 */
@FeignClient("product-service")
public interface ProductProxyService {

	@GetMapping("/product")
	public List<Product> findAllProduct();

	@GetMapping("/product/{id}")
	public Product findProduct(@PathVariable("id") int productId);

}
