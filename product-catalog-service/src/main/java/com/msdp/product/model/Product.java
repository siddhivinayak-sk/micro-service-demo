package com.msdp.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author karuneshkumar.s
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String productType;
	private String returnable;
	private float price;
	private float weight;
	private String brand;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeOfProduct() {
		return productType;
	}

	public void setTypeOfProduct(String typeOfProduct) {
		this.productType = typeOfProduct;
	}

	public String getReturnable() {
		return returnable;
	}

	public void setReturnable(String returnable) {
		this.returnable = returnable;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
