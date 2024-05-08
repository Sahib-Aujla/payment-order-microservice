package com.payments.paymentService.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments.paymentService.models.PaymentRequest;
import com.payments.paymentService.models.PaymentResponse;
import com.payments.paymentService.services.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {
	
	private PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	@GetMapping("all")
	public ResponseEntity<List<PaymentResponse>> getPayments(){
		return new ResponseEntity<>(paymentService.getAllPayments(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest){
		return new ResponseEntity<>(paymentService.processPayment(paymentRequest),HttpStatus.OK);
	}
	
	
	
}
