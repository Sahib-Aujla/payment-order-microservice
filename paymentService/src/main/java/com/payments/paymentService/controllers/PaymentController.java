package com.payments.paymentService.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
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
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(PaymentController.class);
	private PaymentService paymentService;
	
	public PaymentController(PaymentService paymentService) {
		this.paymentService=paymentService;
	}
	
	
	// For demonstration purposes only for the frontend
	@GetMapping("/all")
	public ResponseEntity<List<PaymentResponse>> getPayments(){
		
		log.info("Get request received to get all payments");
		return new ResponseEntity<>(paymentService.getAllPayments(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest){
		log.info("Post request received to process payment");
		return new ResponseEntity<>(paymentService.processPayment(paymentRequest),HttpStatus.OK);
	}
	
	
	
}
