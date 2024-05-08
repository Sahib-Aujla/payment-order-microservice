package com.order.api.controllers;

import java.util.List;

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
	
	public OrderController(OrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping
	public ResponseEntity<List<Order>> getAllOrders(){
		return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest) {
		
        return new ResponseEntity<>(orderService.saveOrder(orderRequest),HttpStatus.CREATED);
    }
	
	@PutMapping("/update/{id}")
	public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest orderRequest,@PathVariable int id){
		OrderResponse orderResponse=orderService.updateOrder(orderRequest,id);
		if(orderResponse!=null) {
			return new ResponseEntity<>(orderResponse,HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable int id){
		boolean isDeleted=orderService.deleteOrder(id);
		
		if(isDeleted) {
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<>("Deletion Unsuccessful",HttpStatus.OK); 
	}

	
}
