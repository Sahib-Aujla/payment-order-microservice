package com.order.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.api.entities.Order;


public interface OrderRepo extends JpaRepository<Order,Integer>{

}
