package com.ace.msdp.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ace.msdp.order.model.Product;

/**
 * @author karuneshkumar.s
 *
 */
@FeignClient("product-catalogue")
public interface ProductProxyService {

	@GetMapping("/product/{id}")
	public Product findProduct(@PathVariable("id") int productId);

}
