package com.payments.paymentService.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.payments.paymentService.entities.Payment;
import com.payments.paymentService.exception.CustomException;
import com.payments.paymentService.models.PaymentRequest;
import com.payments.paymentService.models.PaymentResponse;
import com.payments.paymentService.models.PaymentStatus;
import com.payments.paymentService.repositories.PaymentRepo;
import com.payments.paymentService.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
	private PaymentRepo paymentRepo;

	public PaymentServiceImpl(PaymentRepo paymentRepo) {
		this.paymentRepo = paymentRepo;
	}

	@Override
	public PaymentResponse processPayment(PaymentRequest paymentRequest) {
		log.info("starting to process payment");
		if (paymentRequest == null) {
			log.error("payment request body is null");
			throw new CustomException("Payment Request Invalid", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value());
		}
		if (paymentRequest.getOrderId() <= 0 || paymentRequest.getReferenceNumber() == null
				|| paymentRequest.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
			log.error("payment request data invalid");
			throw new CustomException("Payment Request Invalid", HttpStatus.NOT_ACCEPTABLE,
					HttpStatus.NOT_ACCEPTABLE.value());
		}

		Payment payment = new Payment(paymentRequest.getOrderId(), paymentRequest.getPaymentAmount(), Instant.now(),
				PaymentStatus.ACCEPTED, paymentRequest.getReferenceNumber());
		try {
			this.paymentRepo.save(payment);
		} catch (Exception e) {
			log.error("Error saving to database");
			payment.setPaymentStatus(PaymentStatus.DECLINED);
			new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentAmount(),
					payment.getPaymentDate(), payment.getPaymentStatus());
		}

		return new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getPaymentAmount(),
				payment.getPaymentDate(), payment.getPaymentStatus());

	}

	@Override
	public List<PaymentResponse> getAllPayments() {
		log.info("sending all payments");

		List<PaymentResponse> paymentResponseList = new ArrayList<PaymentResponse>();
		try {
			List<Payment> payments = this.paymentRepo.findAll();

			for (var payment : payments) {
				paymentResponseList.add(new PaymentResponse(payment.getId(), payment.getOrderId(),
						payment.getPaymentAmount(), payment.getPaymentDate(), payment.getPaymentStatus()));
			}
		} catch (Exception e) {
			log.error("Error saving to database: " + e.getMessage());
			throw new CustomException("Server error", HttpStatus.SERVICE_UNAVAILABLE,
					HttpStatus.SERVICE_UNAVAILABLE.value());
		}

		return paymentResponseList;
	}

}
