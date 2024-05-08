package com.payments.paymentService.models;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentResponse {
	private int paymentId;
	private int orderId;
	private BigDecimal paymentAmount;
	private Instant paymentDate;
	private PaymentStatus paymentStatus;
	public PaymentResponse(int paymentId, int orderId, BigDecimal paymentAmount, Instant paymentDate,
			PaymentStatus paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Instant getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Instant paymentDate) {
		this.paymentDate = paymentDate;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
}
