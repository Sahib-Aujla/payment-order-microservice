package com.order.api.models;

import java.math.BigDecimal;

public class OrderRequest {
	private String customerName;
	private String productName;
	private BigDecimal totalPrice;
	private int quantity;

	public OrderRequest(String customerName,String productName, BigDecimal totalPrice, int quantity) {
		super();
		this.customerName = customerName;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
		this.productName=productName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getTotalAmount() {
		return totalPrice;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalPrice = totalAmount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
