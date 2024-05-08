package com.order.api.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.api.entities.Order;
import com.order.api.models.OrderRequest;
import com.order.api.models.OrderResponse;
import com.order.api.services.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
	private OrderService orderService;
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(OrderService.class);

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders() {
		log.info("request received for getAllOrders.");
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest) {
		log.info("post request received to save new order");
		return new ResponseEntity<>(orderService.saveOrder(orderRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable int id) {
		log.info("Put request received to update with orderId: " + id);
		OrderResponse orderResponse = orderService.updateOrder(orderRequest, id);

		return new ResponseEntity<>(orderResponse, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id) {
		boolean isDeleted = orderService.deleteOrder(id);
		log.info("delete request received with orderId: " + id);
		if (isDeleted) {
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Deletion Unsuccessful", HttpStatus.NOT_FOUND);
	}

}
