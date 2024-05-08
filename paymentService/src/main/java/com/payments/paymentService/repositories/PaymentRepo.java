package com.payments.paymentService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payments.paymentService.entities.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{
	
}
