package com.payments.paymentService.services;

import java.util.List;

import com.payments.paymentService.models.PaymentRequest;
import com.payments.paymentService.models.PaymentResponse;

public interface PaymentService  {
	
	PaymentResponse processPayment(PaymentRequest paymentRequest);
	
	List<PaymentResponse> getAllPayments();
}
