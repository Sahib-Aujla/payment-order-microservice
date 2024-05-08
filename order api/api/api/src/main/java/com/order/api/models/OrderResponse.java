package com.order.api.models;

import java.math.BigDecimal;
import java.time.Instant;

import com.order.api.entities.PaymentStatus;

public class OrderResponse {
	private int orderId;
	private String customerName;
	private String ProductName;
    private Instant orderDate;
    private PaymentStatus paymentStatus;
    private BigDecimal totalPrice;
	
	public OrderResponse(int orderId, String customerName, String productName, Instant orderDate,
			PaymentStatus paymentStatus, BigDecimal totalPrice) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		ProductName = productName;
		this.orderDate = orderDate;
		this.paymentStatus = paymentStatus;
		this.totalPrice = totalPrice;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Instant getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
    
    
}
