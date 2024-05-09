package com.order.api.models;

import java.math.BigDecimal;

public class PaymentRequest {
	private int orderId;

	private String referenceNumber;

	private BigDecimal paymentAmount;

	
	
	public PaymentRequest(int orderId, String referenceNumber, BigDecimal paymentAmount) {
		super();
		this.orderId = orderId;
		this.referenceNumber = referenceNumber;
		this.paymentAmount = paymentAmount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	
}
