package com.order.api.services;

import java.util.List;

import com.order.api.entities.Order;
import com.order.api.models.OrderRequest;
import com.order.api.models.OrderResponse;

public interface OrderService {
	List<Order> getAllOrders();
	
	OrderResponse updateOrder(OrderRequest order,int id);
	
	OrderResponse saveOrder(OrderRequest orderRequest);
	
	boolean deleteOrder(int id);
	
}
