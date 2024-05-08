package com.payments.paymentService.entities;

import java.math.BigDecimal;
import java.time.Instant;

import com.payments.paymentService.models.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int Id;

	@Column(name = "order_id")
	private int orderId;

	@Column(name = "payment_amount")
	private BigDecimal paymentAmount;

	@Column(name = "payment_date")
	private Instant paymentDate;

	@Column(name = "payment_status")
	private PaymentStatus paymentStatus;

	@Column(name = "reference_number", unique = true)
	private String referenceNumber;

	public Payment() {
	}

	public Payment(int orderId, BigDecimal paymentAmount, Instant paymentDate, PaymentStatus paymentStatus,
			String referenceNumber) {
		super();
		this.orderId = orderId;
		this.paymentAmount = paymentAmount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.referenceNumber = referenceNumber;
	}

	public int getId() {
		return Id;
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

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}
