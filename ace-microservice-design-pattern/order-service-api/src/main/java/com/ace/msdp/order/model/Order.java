package com.ace.msdp.order.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author karuneshkumar.s
 *
 */
@Entity
@Table(name = "OrderDetails")
public class Order {

	@Id
	@GeneratedValue
	private int id;
	private int customerId;
	private int productId;
	private float orderValue;
	private int qty;

	private String shippingAddress;

	public float getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(float f) {
		this.orderValue = f;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
