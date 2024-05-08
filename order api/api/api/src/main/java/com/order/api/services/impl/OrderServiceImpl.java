package com.order.api.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.api.entities.Order;
import com.order.api.exception.CustomException;
import com.order.api.models.OrderRequest;
import com.order.api.models.OrderResponse;
import com.order.api.models.PaymentRequest;
import com.order.api.models.PaymentResponse;
import com.order.api.models.PaymentStatus;
import com.order.api.repositories.OrderRepo;
import com.order.api.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	private OrderRepo orderRepo;
	private RestTemplate restTemplate;

	public OrderServiceImpl(OrderRepo orderRepo, RestTemplate restTemplate) {
		this.orderRepo = orderRepo;
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Order> getAllOrders() {
		log.info("Get all orders request received");
		return this.orderRepo.findAll();
	}

	@Override
	public OrderResponse updateOrder(OrderRequest orderRequest, int id) {

		log.info("Update request received for order Id: " + id);

		Order order = orderRepo.findById(id)
				.orElseThrow(() -> new CustomException("Order with id " + id + " not found!", HttpStatus.NOT_FOUND,
						HttpStatus.NOT_FOUND.value()));

		order.setCustomerName(orderRequest.getCustomerName());
		order.setProductName(orderRequest.getProductName());
		order.setQuantity(orderRequest.getQuantity());
		order.setTotalPrice(orderRequest.getTotalAmount());
		try {
			orderRepo.save(order);
			log.info("Order updated for order Id: " + id);
		}catch(Exception e) {
			log.error("Error saving to database: "+e.getMessage());
			throw new CustomException("Server error",HttpStatus.SERVICE_UNAVAILABLE,HttpStatus.NOT_FOUND.value());
		}
	
		return new OrderResponse(order.getId(), order.getCustomerName(), order.getProductName(), order.getOrderDate(),
				order.getPaymentStatus(), order.getTotalPrice());

	}

	@Override
	public OrderResponse saveOrder(OrderRequest orderRequest) {
		if (orderRequest == null) {
			throw new CustomException("Order Request Invalid", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value());
		}
		if (orderRequest.getCustomerName() == null || orderRequest.getProductName() == null
				|| orderRequest.getQuantity() <= 0 || orderRequest.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new CustomException("Order Request Invalid", HttpStatus.NOT_ACCEPTABLE,
					HttpStatus.NOT_ACCEPTABLE.value());
		}
		log.info("Request received for adding new order.");
		Order order = new Order(orderRequest.getCustomerName(), orderRequest.getProductName(),
				orderRequest.getQuantity(), orderRequest.getTotalAmount(), PaymentStatus.PROCESSING, Instant.now());

		orderRepo.save(order);

		int num = ((int) (Math.random() * 6) + 9);

		PaymentRequest paymentRequest = new PaymentRequest(order.getId(),
				UUID.randomUUID().toString().substring(1, num), order.getTotalPrice());

		try {
			PaymentResponse response = restTemplate.postForObject("http://localhost:8081/payment", paymentRequest,
					PaymentResponse.class);
			order.setPaymentStatus(response.getPaymentStatus());

		} catch (Exception e) {
			log.error("Error processing the payment. " + e.getMessage());
			order.setPaymentStatus(PaymentStatus.DECLINED);
		}
		orderRepo.save(order);
		log.info("order saved successfully");

		return new OrderResponse(order.getId(), order.getCustomerName(), order.getProductName(), order.getOrderDate(),
				order.getPaymentStatus(), order.getTotalPrice());
	}

	@Override
	public boolean deleteOrder(int id) {
		log.info("Request received for deleting order for order id: " + id);
		if (orderRepo.existsById(id)) {
			orderRepo.deleteById(id);
			log.info("order deleted with orderId : "+id);
			return true;
		}
		log.info("Order does not exist with orderId: "+id);
		return false;

	}

}
