package com.order.api.entities;

import java.math.BigDecimal;
import java.time.Instant;

import com.order.api.models.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int Id;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "order_date")
	private Instant orderDate;

	@Enumerated
	@Column(name = "payment_status")
	private PaymentStatus paymentStatus;

	public Order() {
	}

	public Order(String customerName, String productName, int quantity, BigDecimal totalPrice,
			PaymentStatus paymentStatus, Instant orderDate) {
		super();
		this.customerName = customerName;
		this.productName = productName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.paymentStatus = paymentStatus;
		this.orderDate = orderDate;
	}

	public int getId() {
		return Id;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
