package com.msdp.product.controller;

import java.util.List;

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

import com.msdp.product.model.Product;
import com.msdp.product.service.ProductCatalogueService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author karuneshkumar.s
 *
 */
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/product", description = "Opeartions pertaining to Product Catalogue Management")
public class ProductCatalogueController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueController.class);

	@Autowired
	private ProductCatalogueService productCatalogueService;

	@PostMapping
	@ApiOperation(value = "Creates the product into the system against the given product Id")
	public Product addProductDetails(@RequestBody Product product) {
		LOGGER.info("Executing ProductCatalogueController: addProductDetails method");
		return productCatalogueService.addProductDetails(product);
	}

	@GetMapping
	@ApiOperation(value = "Retrieves all Product")
	public List<Product> findAllProduct() {
		LOGGER.info("Executing ProductCatalogueController: findAllProduct method");
		return productCatalogueService.getAllProduct();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves the Product details against the given Product ID.")
	public Product findProduct(@PathVariable("id") int productId) {
		LOGGER.info("Executing ProductCatalogueController: findProduct method");
		return productCatalogueService.getProduct(productId);
	}

	@PutMapping
	@ApiOperation(value = "Updates the Product details into the system")
	public Product updateProduct(@RequestBody Product product) {
		LOGGER.info("Executing ProductCatalogueController: updateProduct method");
		return productCatalogueService.updateProduct(product);
	}

	@DeleteMapping("/{productId}")
	@ApiOperation(value = "Deletes the details of given product from the system")
	public void deleteProduct(@PathVariable("productId") int productId) {
		LOGGER.info("Executing ProductCatalogueController: deleteProduct method");
		productCatalogueService.deleteProduct(productId);
	}

	@GetMapping("/poll")
	@ApiOperation(value = "This endpoint is exposed to test if the service is up and running")
	public String testGetMethod() {
		LOGGER.info("Executing ProductCatalogueController: testGetMethod method");
		return "YES";
	}

}
